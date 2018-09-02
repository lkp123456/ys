package com.xxx.crawler;

import com.xxx.entity.Vod;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.ExecutionException;

public class Test {

    public static void main(String[] args) throws Exception {
        String url = "http://www.ygdy8.com/html/gndy/jddy/20180815/57291.html";
        Document document = Jsoup.connect(url).get();


        Vod vod = new Vod();
        int j = url.lastIndexOf("/");
        String strPublishDate = url.substring(j - 8, j);
        Date publishDate = DateUtils.parseDate(strPublishDate, "yyyyMMdd");
        vod.setPublishDate(publishDate);
        //vodType 0 电影 1 电视
        if (url.contains("/gndy/")) {
            vod.setVodType(0);
        } else if (url.contains("/tv/")) {
            vod.setVodType(1);
        }
        //countryType 0国内 1欧美 2日韩

//            if (url.contains("/jddy/")) {
//                vod.setCountryType(0);
//            } else if (url.contains("/oumei/")) {
//                vod.setCountryType(1);
//            } else if (url.contains("gndy/rihan/")) {
//                vod.setCountryType(2);
//            } else if (url.contains("/hytv/")) {
//                vod.setCountryType(0);
//            } else if (url.contains("/oumeitv/")) {
//                vod.setCountryType(1);
//            } else if (url.contains("/rihantv/")) {
//                vod.setCountryType(2);
//            }else {
//                vod.setCountryType(0);
//            }

        vod.setCountryType(1);

        Elements h1 = document.getElementsByTag("h1");
        for (Element element : h1) {
            Elements font = element.getElementsByTag("font");
            String title = font.text();
            vod.setTitle(title);//title
            System.out.println(title);
            int i = title.indexOf("《");
            int i1 = title.indexOf("》");
            String name = title.substring(i + 1, i1);
            vod.setName(name);
        }

        Element zoom = document.getElementById("Zoom");//只有1个
        Elements imgs = zoom.getElementsByTag("img");//两个
        for (int i = 0; i < imgs.size(); i++) {
            Element img = imgs.get(i);
            String src = img.attr("src");
            if (i == 0) {
                vod.setPostUrl(src);//postUrl
            }
            if (i == 1) {
                vod.setScreenshotUrl(src);//ScreenshotUrl
            }
        }

        Elements ps = zoom.getElementsByAttributeValue("style", "FONT-SIZE: 12px");
        StringBuilder stringBuilder = new StringBuilder();
        for (Element p : ps) {
            String ownText = p.text();
            String[] split = ownText.split("◎");

            for (int i = 1; i < split.length; i++) {
                if (split[i].contains("主　　演")) {
                    split[i] = split[i].replace(" 　　　　　　", "/");
                }
                stringBuilder.append("<br>◎" + split[i]);
            }
        }
        System.out.println(stringBuilder.toString());
        vod.setContent(stringBuilder.toString());
        System.out.println(vod);
        Elements downloadUrls = zoom.getElementsByTag("a");
        System.out.println("===============================");
        HashSet<String> urls = new HashSet<>();
        for (int i = 0; i < downloadUrls.size(); i++) {
            Element element = downloadUrls.get(i);
            String href = element.attr("href");
            System.out.println(href);
            urls.add(href);
        }



    }
}
