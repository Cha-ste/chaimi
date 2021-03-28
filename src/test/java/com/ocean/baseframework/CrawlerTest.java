package com.ocean.baseframework;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CrawlerTest {
    private String URL = "https://www.vvic.com/gz/markets.html";
    private String URL_PREFIX = "https://www.vvic.com";
    @Test
    public void test() {
        List<String> marketMapList = new ArrayList<>();
        List<String> shopList = new ArrayList<>();
        Document document = Jsoup.parse(downloadPage(URL, false));
        Elements marketList = document.getElementsByClass("mk-list").first().getElementsByTag("a");
        for(Element element : marketList) {
            String href = element.attr("href");
            marketMapList.add(URL_PREFIX + href);
        }

        Document dxh = Jsoup.parse(downloadPage(marketMapList.get(1), false));
        Elements shopLink = dxh.getElementsByClass("items j-vda");
//        dxh.select();
        for(Element element : shopLink) {
            String href = element.attr("href");
            shopList.add(URL_PREFIX + href);
        }

//        Document shop = Jsoup.parse(downloadPage(shopList.get(1), true));
//        Elements vda = shop.getElementsByAttributeValue("vda", "action|status|allgoods");
//        String p = vda.attr("href"); // 店铺在售商品页
//        Document product = Jsoup.parse(downloadPage(URL_PREFIX + p, false));
//        Elements items = product.getElementsByClass("goods-list shop-list clearfix");

        int num = 0;
        for (String sl : shopList) {
            Document shop = Jsoup.parse(downloadPage(sl, true));
            Elements vda = shop.getElementsByAttributeValue("vda", "action|status|allgoods");
            String p = vda.attr("href"); // 店铺在售商品页
            Document product = Jsoup.parse(downloadPage(URL_PREFIX + p, true));
            Elements items = product.getElementsByClass("goods-list shop-list clearfix");
            if(num == 54) {
                System.out.println("=========异常预警=========");
            }
            System.out.println("==================================" + num ++ );
        }
    }

    private String downloadPage(String url, Boolean setHeader) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
        HttpGet httpGet = new HttpGet(url);

        if(setHeader) {
            setRequestHeader(httpGet, url);
        }

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void setRequestHeader(HttpGet httpGet, String url) {
//        httpGet.setHeader(":authority", "www.vvic.com");
//        httpGet.setHeader(":method", "GET");
//        httpGet.setHeader(":path", url.substring(URL_PREFIX.length()));
//        httpGet.setHeader(":scheme", "https");
//        httpGet.setHeader("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
//        httpGet.setHeader("accept-encoding", "gzip, deflate, br");
//        httpGet.setHeader("accept-language", "zh-CN,zh;q=0.9");
//        httpGet.setHeader("cache-control", "max-age=0");
//        httpGet.setHeader("sec-fetch-mode", "navigate");
//        httpGet.setHeader("sec-fetch-site", "same-origin");
//        httpGet.setHeader("sec-fetch-user", "?1");
//        httpGet.setHeader("upgrade-insecure-requests", "1");

//        httpGet.setHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.130 Safari/537.36");
//        String resultUrl = url.substring(0, url.lastIndexOf("?")).replace("/list", "");
//        httpGet.setHeader("referer", resultUrl);
//        httpGet.setHeader("referer", "https://www.vvic.com/gz/shops/12");
        httpGet.setHeader("cookie", "acw_tc=3b24e21e15873908614105661ec61989ff47ed6388fa3ff076ec0b2d2a; cu=BE78A3C400780C900F1CD31C30EDB6C1; chash=1406620679; _countlyIp=113.119.75.54; _uab_collina=158739086270522413494885; _ga=GA1.2.1906595763.1587390863; _gid=GA1.2.144684982.1587390863; city=gz; ISSUPPORTPANGGE=true; DEVICE_INFO=%7B%22device_id%22%3A%22BE78A3C400780C900F1CD31C30EDB6C1%22%2C%22device_channel%22%3A1%2C%22device_type%22%3A1%2C%22device_model%22%3A%22Windows%22%2C%22device_os%22%3A%22Windows%2010%22%2C%22device_lang%22%3A%22zh-CN%22%2C%22device_size%22%3A%221920*1080%22%2C%22device_net%22%3A%220%22%2C%22device_lon%22%3A113.30764968%2C%22device_lat%22%3A23.1200491%2C%22device_address%22%3A%22%E5%B9%BF%E4%B8%9C%E7%9C%81%E5%B9%BF%E5%B7%9E%E5%B8%82%22%2C%22browser_type%22%3A%22Chrome%22%2C%22browser_version%22%3A%2279.0.3945.130%22%7D; rankTipShow=1; user_key=YjNiNTE5NTQtMjM4MS00YzlhLWIzZmMtYTgyMThiNmUxNDhh; _ADDRESSTYPE=add; temporary_user_key=YjNiNTE5NTQtMjM4MS00YzlhLWIzZmMtYTgyMThiNmUxNDhh; rankCateogry=10001; ORDERTYPE=1; ipCity=59.42.2.193%2C%E5%B9%BF%E4%B8%9C%E7%9C%81%20%E5%B9%BF%E5%B7%9E%E5%B8%82; _MYBID=%7B%22gz%22%3A%2212%22%7D; route=d736ac764983ff2b8247885a547d9fc9; vvic_token=8eb22bf6-71af-41b5-ba6f-4138556dc2f5; Hm_lvt_fdffeb50b7ea8a86ab0c9576372b2b8c=1587566045,1587649846; algo4Cu=124; Hm_lpvt_fdffeb50b7ea8a86ab0c9576372b2b8c=1587649856");
    }
}
