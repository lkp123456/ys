package com.xxx.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.ExecutionException;

public class Test {

    public static void main(String[] args) throws Exception {
        Document document = Jsoup.connect("http://www.ygdy8.com/html/gndy/dyzz/20180831/57350.html").get();
        String url = "/html/gndy/dyzz/20180831/57350.html";
        int k = url.lastIndexOf("/");
        String publishdate = url.substring(k - 8, k);
        System.out.println(publishdate);

        Elements h1 = document.getElementsByTag("h1");
        for (Element element : h1) {
            Elements font = element.getElementsByTag("font");
            String title = font.text();
            System.out.println(title);
            int i = title.indexOf("《");
            int i1 = title.indexOf("》");
            String name = title.substring(i + 1, i1);
            System.out.println(name);
        }
        for (Element element : h1) {
            Elements font = element.getElementsByTag("font");
            String text = font.text();
            System.out.println(text);
        }
        Element zoom = document.getElementById("Zoom");//只有1个
        Elements imgs = zoom.getElementsByTag("img");//两个
        for (int i = 0; i < imgs.size(); i++) {
            Element img = imgs.get(i);
            String src = img.attr("src");
            System.out.println(src);
            if (i == 0) {
            }
            if (i == 1) {
            }
        }
        Elements ps = zoom.getElementsByTag("a");
        System.out.println("===============================");
        for (Element p : ps) {
            String href = p.attr("href");
            System.out.println(href);
        }


    }
}
