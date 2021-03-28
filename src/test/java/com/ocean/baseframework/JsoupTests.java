package com.ocean.baseframework;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/**
 * jsoup 爬虫测试
 */
public class JsoupTests {
    private String url = "http://quote.eastmoney.com/us/IQ.html?from=BaiduAladdin";
    private File file = new File("C:\\Users\\Admin\\Desktop\\爱奇艺(IQ)股票价格_行情_走势图—东方财富网.html");

    private static PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
    @Test
    public void testDoc() throws Exception {
        Document document = Jsoup.parse(file, "utf-8");
        String rate = document.getElementsByClass("zxj red").first().text();
        Element upOrDownNode = document.getElementById("arrow-find");
        String upOrDown = null;
        if(upOrDownNode.hasClass("up-arrow")) {
            upOrDown = "上升";
        } else {
            upOrDown = "下降";
        }

        System.out.println(upOrDown + "：" + rate);

    }

    public void testHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
    }

}
