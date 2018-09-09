package com.xxx.crawler;

import com.alibaba.fastjson.JSON;
import com.xxx.entity.Vod;
import com.xxx.utils.HttpClientFactory;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.ParseException;
import java.util.*;
import java.util.concurrent.*;


/**
 * @Auther: Administrator
 * @Date: 2018/8/13 11:19
 * @Description:
 */
public class DayCrawler {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(50, 800, 200, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private static String baseUrl = "http://www.ygdy8.net";

    public static void main(String[] args) throws InterruptedException, ExecutionException, ParseException {


        CompletionService<Map> objectCompletionService = new ExecutorCompletionService<>(pool);
//        int taskSize = 34;
//        for (int i = 1; i <= taskSize; i++) {
//            String url = "http://www.ygdy8.net/html/gndy/rihan/list_6_"+i+".html";
//            HttpClientFactory instance = HttpClientFactory.getInstance();
//            DayCrawler.GetHtmlTask getHtmlTask = new DayCrawler().new GetHtmlTask(null, instance, url);
//            Thread.sleep(10);
//            objectCompletionService.submit(getHtmlTask);
//            System.out.println("submit task size = " + i);
//        }
        //获取首页文档
        HttpClientFactory instance = HttpClientFactory.getInstance();
        DayCrawler.GetHtmlTask getHtmlTask = new DayCrawler().new GetHtmlTask(null, instance, baseUrl);
        objectCompletionService.submit(getHtmlTask);


        //解析首页文档url
        CompletionService<List<String>> parseCompletionService = new ExecutorCompletionService<>(pool);
        Future<Map> future = objectCompletionService.take();
        Map map = future.get();
        //拿到html数据解析URL
        DayCrawler.ParseHtmlTask parseHtmlTask = new DayCrawler().new ParseHtmlTask((Document) map.get("document"));
        parseCompletionService.submit(parseHtmlTask);

        Future<List<String>> take1 = parseCompletionService.take();
        List<String> urls = null;
        if (take1.isDone()) {
            urls = take1.get();
        }

        System.out.println("详情页数据量：" + urls.size());

        System.out.println("===================================================");
        System.out.println("===================================================");

        int i = 0;
        for (String url : urls) {
            String detailUrl = baseUrl + url;
            System.out.println("@@@@@@@@@@@@@" + detailUrl);
            System.out.println("==================start commit detail page===========" + (i++));
            DayCrawler.GetHtmlTask getDetailHtmlTask = new DayCrawler().new GetHtmlTask(null, instance, detailUrl);
            objectCompletionService.submit(getDetailHtmlTask);
        }

        System.out.println("==================start parse detail page=================================");
        CompletionService<Map> parseDetailHtmlTaskService = new ExecutorCompletionService<>(pool);
        int j = 0;
        for (String url : urls) {
            try {
                Future<Map> detailFuture = objectCompletionService.take();
                Map detailFutureMap = detailFuture.get();
                Document document = (Document) detailFutureMap.get("document");
                String url1 = (String) detailFutureMap.get("url");
                System.out.println("==================start  parseDetailHtmlTask===================" + (j++));
                DayCrawler.ParseDetailHtmlTask parseDetailHtmlTask = new DayCrawler().new ParseDetailHtmlTask(document, url1);
                parseDetailHtmlTaskService.submit(parseDetailHtmlTask);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("==================start  get  vod=================================");

        for (String url : urls) {
            try {
                Future<Map> take = parseDetailHtmlTaskService.take();

                boolean done = take.isDone();
                if (done) {
                    Map detailMap = take.get();
                    detailMap.put("key", "qewqwasdasdsfsqwaawxcfgcegfcd");
                    String s = JSON.toJSONString(detailMap);
                    String s1 = instance.postData("http://cinemaparadise.club/addVod", s);
//                    String s1 = instance.postData("http://localhost:8080/addVod", s);
                    System.out.println(s1);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        pool.shutdown();
    }


    //============================================================================================================


    private class ParseHtmlTask implements Callable<List<String>> {

        List hrefList = new LinkedList<String>();
        private Document htmlData;

        public ParseHtmlTask(Document htmlData) {
            this.htmlData = htmlData;
        }

        @Override
        public List<String> call() throws Exception {
            Elements as = new Elements();
            Elements co_content2 = htmlData.getElementsByClass("co_content2");
            for (Element co_content : co_content2) {
                Elements innerAs = co_content.getElementsByTag("a");
                as.addAll(innerAs);
            }
            for (Element a : as) {
                String href1 = a.attr("href");
                int j = href1.lastIndexOf("/");
                if (j <= 8) {
                    continue;
                }
                String strPublishDate = href1.substring(j - 8, j);
                if (strPublishDate.compareTo("20180905") >= 0) {
                    hrefList.add(href1);
                }
            }
            return hrefList;
        }
    }

    private class GetHtmlTask implements Callable<Map> {
        private Map headers;
        private HttpClientFactory instance;
        private String url;

        public GetHtmlTask(Map headers, HttpClientFactory instance, String url) {
            this.headers = headers;
            this.instance = instance;
            this.url = url;
        }

        @Override
        public Map call() throws Exception {
            Document document = Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36")
                    .timeout(10000)
                    .get();
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("document", document);
            objectObjectHashMap.put("url", url);
            return objectObjectHashMap;
        }
    }


    private class ParseDetailHtmlTask implements Callable<Map> {

        private Document htmlData;
        private String url;

        public ParseDetailHtmlTask(Document htmlData, String url) {
            this.htmlData = htmlData;
            this.url = url;
        }

        @Override
        public Map call() throws Exception {

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


            Elements h1 = htmlData.getElementsByTag("h1");
            for (Element element : h1) {
                Elements font = element.getElementsByTag("font");
                String title = font.text();
                vod.setTitle(title);//title
                int m = title.indexOf("《");
                int n = title.indexOf("》");

                System.out.println(title);
                System.out.println(m + "   " + n);
                String name = title.substring(m + 1, n);
                System.out.println(name);

                vod.setName(name);
            }

            Element zoom = htmlData.getElementById("Zoom");//只有1个
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
            StringBuffer stringBuffer = new StringBuffer();
            for (Element p : ps) {
                Elements p1 = p.getElementsByTag("p");

                for (Element element : p1) {
                    String ownText = element.text();

                    String[] split = ownText.split("◎");

                    for (int i = 1; i < split.length; i++) {
                        if (split[i].contains("产　　地") && split[i].contains("中国")) {
                            vod.setCountryType(0);
                        } else if (split[i].contains("产　　地") && (split[i].contains("日本") || split[i].contains("韩国"))) {
                            vod.setCountryType(2);
                        } else if(split[i].contains("产　　地")){
                            vod.setCountryType(1);
                        }
                        stringBuffer.append("<br>◎" + split[i]);
                    }
                }
            }
            String s = stringBuffer.toString();
            int i1 = s.indexOf("【");
            String substring = "";
            if (i1 > 1) {
                substring = s.substring(0, i1);
            } else {
                substring = s;
            }
            vod.setContent(substring);

            Elements downloadUrls = zoom.getElementsByTag("a");
            System.out.println("===============================");

            HashSet<String> urls = new HashSet<>();
            for (int i = 0; i < downloadUrls.size(); i++) {
                Element element = downloadUrls.get(i);
                String href = element.attr("href");
                if(href.equals("")||href.startsWith("http://www.ygdy8.com/")){
                    continue;
                }
                urls.add(href);
            }
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("vod", vod);
            objectObjectHashMap.put("urls", urls);

            return objectObjectHashMap;
        }
    }

}
