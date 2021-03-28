package com.ocean.baseframework;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DemoProcessor implements PageProcessor {
    private final String DOMAIN = "https://www.vvic.com";
    private final String ON_SALE_SUFFIX = "?go=content_all";
    private final String DETAIL_PREFIX = "https://www.vvic.com/item";

    // 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
    public void process(Page page) {
        /**
         * 思路：
         * 1、从市场导航获取广州地区的所有市场
         * 2、进入每个市场获取店铺
         * 3、进入店铺在售商品页获取商家信息
         * 4、进入商品详情页获取商品信息
         *
         * 由于商品是本次爬虫数量最多的，所以把最上层if用作判断是否为商品
         * 最大化减少if判断次数，以此类推
         */

        // 商品详情
        if(page.getRequest().getUrl().contains(DETAIL_PREFIX)) {
            saveProduct(page);
        } else {
            // 在售商品
            if (page.getRequest().getUrl().contains(ON_SALE_SUFFIX)) {
                saveShopInfo(page);
                List<String> productList = page.getHtml().css(".goods-list.shop-list.clearfix .item a").links().all();
                page.addTargetRequests(productList);
            } else {
                // 店铺
                if (page.getHtml().$("div.mk-shops.mt10 .items").nodes().size() > 0) {
                    List<String> shopList = page.getHtml().css("div.mk-shops.mt10 .items").links().all();
                    shopList = shopList.stream().map(s -> convertLink(s)).collect(Collectors.toList());
                    page.addTargetRequests(shopList);
                } else {
                    // 市场
                    if(page.getHtml().$(".mk-list .item a").nodes().size() > 0) {
                        List<String> marketList = page.getHtml().css(".mk-list .item a").links().all();
                        page.addTargetRequests(marketList);
                    }
                }
            }
        }

    }

    /**
     * 保存店铺信息
     */
    private void saveShopInfo(Page page) {
        Html html = page.getHtml();
        List<Selectable> infoItems = html.css(".shop-content li").nodes();
        Map<String, Selectable> map = new HashMap<>();
        for (Selectable item : infoItems) {
            String key = item.css(".attr", "text").get();
            Selectable value = item.css(".text");
            map.put(key, value);
        }

        String shopName = html.css(".shop-content .shop-name span", "text").get();// 店铺名称
        String rank = html.css(".text-top-num", "text").get(); // 排名
        String wwName = html.css(".shop-content span.fl", "text").get(); // 旺旺名
        String origin = map.get("产地：").css(".text", "text").get(); // 产地
        String address = map.get("地址：").css(".text", "text").get(); // 地址

        String mainSale = null;
        if(map.get("主营：") != null) {
            mainSale = map.get("主营：").get();
        }

        String phone = null;
        if (map.get("电话：") != null) {
            Selectable selectable = map.get("电话：");
            List<String> allNum = selectable.css("span[class]", "text").all();
            StringBuilder sb = new StringBuilder();
            for(String n : allNum) {
                sb.append(n);
            }
            phone = sb.toString();
        }

        String weChat = null;
        if (map.get("微信：") != null) {
            Selectable selectable = map.get("微信：");
            List<String> allNum = selectable.css("span:nth-of-type(2n-1)", "text").all();
            StringBuilder sb = new StringBuilder();
            for(String n : allNum) {
                sb.append(n);
            }
            weChat = sb.toString();
        }

        String qq = null;
        if (map.get("QQ：") != null) {
            Selectable selectable = map.get("QQ：");
            List<String> allNum = selectable.css("span:nth-of-type(2n-1)", "text").all();
            StringBuilder sb = new StringBuilder();
            for(String n : allNum) {
                sb.append(n);
            }
            qq = sb.toString();
        }

        // TODO: 信息保存

    }

    /**
     * 保存商品信息
     */
    private void saveProduct(Page page) {
        Html html = page.getHtml();
        String productName = html.css(".d-name", "text").get(); // 商品名称
        String pPrice = html.css(".v-price.d-p .d-sale", "text").get(); // 批发价 拆分
        List<String> two = html.css(".value.ff-arial", "text").all();
        String pNum = two.get(0); // 货号
        String updTime = two.get(1); // 更新时间


        html.css(".owl-stage .owl-item img","mid").all();//商品图片
        html.css(".d-content p img","src").all();//商品详情图片
        html.css(".d-attr li","title").all();//商品详情描述
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new DemoProcessor()).addUrl("https://www.vvic.com/gz/markets.html").thread(5).run();
    }

    /**
     * 根据店铺地址，转换为店铺在售商品地址
     * @param link
     */
    private String convertLink(String link) {
        String shopId = link.substring(link.lastIndexOf("/") + 1);
        return link.replace(shopId, "list/" + shopId + ON_SALE_SUFFIX);
    }
}
