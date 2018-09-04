package com.xxx.crawler;

import com.alibaba.fastjson.JSON;
import com.xxx.entity.Vod;
import com.xxx.utils.DateTimeUtil;
import com.xxx.utils.HttpClientFactory;
import org.apache.commons.lang3.time.DateUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.ByteArrayInputStream;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.*;


/**
 * @Auther: Administrator
 * @Date: 2018/8/13 11:19
 * @Description:
 */
public class DayCrawler {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 20, 200, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

    private static String baseUrl = "http://www.ygdy8.com";
    static Date crawlDate = null ;

    public static void main(String[] args) throws InterruptedException, ExecutionException, ParseException {
        //main参数指定0:vodType(0:mv 1:tv) 1:countryType(0:china 1:oumei 2:rihan) 2:日期(格式:yyyy-Mm-dd)
        //           3:baseUrl 4:startPath 5:pageSize
        int vodType= Integer.parseInt(args[0]);
        int country= Integer.parseInt(args[1]);
        crawlDate = DateTimeUtil.parseDate(args[2], DateTimeUtil.DATE_FORMAT_NORMAL);
        String startPath = args[3];
        baseUrl = args[4];
        String startUrl =baseUrl+startPath;


        DayCrawler crawler = new DayCrawler();



        CompletionService<String> objectCompletionService = new ExecutorCompletionService<>(pool);
        int taskSize = 3;
        for (int i = 1; i <= taskSize; i++) {
            String url = startUrl + i + ".html";
            HttpClientFactory instance = HttpClientFactory.getInstance();
            DayCrawler.GetHtmlTask getHtmlTask = crawler.new GetHtmlTask(null, instance, url);
            Thread.sleep(10);
            objectCompletionService.submit(getHtmlTask);
            System.out.println("submit task size = " + i);
        }

        CompletionService<List<String>> parseCompletionService = new ExecutorCompletionService<>(pool);
        for (int i = 1; i <= taskSize; i++) {
            Future<String> future = objectCompletionService.take();
            String htmlData = future.get();

            System.out.println("获取第" + i + "页html数据");
            //拿到html数据解析URL

            DayCrawler.ParseHtmlTask parseHtmlTask = crawler.new ParseHtmlTask(htmlData);
            parseCompletionService.submit(parseHtmlTask);

        }

        LinkedHashSet<String> urls = new LinkedHashSet<>();

        for (int i = 0; i < taskSize; i++) {
            Future<List<String>> future = parseCompletionService.take();
            List<String> taskUrls = future.get();
            System.out.println("=======第" + i + "次任务取出详情页url数：" + taskUrls.size());
            for (int j = 0; j < taskUrls.size(); j++) {
                System.out.println("url：" + taskUrls.get(j));
            }
            urls.addAll(taskUrls);
        }
        System.out.println("详情页数据量：" + urls.size());

        System.out.println("===================================================");
        System.out.println("===================================================");

        int i = 0;
        for (String url : urls) {
            String detailUrl = baseUrl + url;
            HttpClientFactory instance = HttpClientFactory.getInstance();
            System.out.println("==================start commit detail page===========" + (i++));
            DayCrawler.GetHtmlTask getHtmlTask = crawler.new GetHtmlTask(null, instance, detailUrl);
            objectCompletionService.submit(getHtmlTask);
        }

        System.out.println("==================start parse detail page=================================");
        CompletionService<Map> parseDetailHtmlTaskService = new ExecutorCompletionService<>(pool);
        int j = 0;
        for (String url : urls) {
            try {
                Future<String> future = objectCompletionService.take();
                String htmlData = future.get();
                System.out.println("==================start  parseDetailHtmlTask===================" + (j++));
                DayCrawler.ParseDetailHtmlTask parseDetailHtmlTask = crawler.new ParseDetailHtmlTask(htmlData, url);
                parseDetailHtmlTaskService.submit(parseDetailHtmlTask);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("==================start  get  vod=================================");

        for (String url : urls) {
            try {
                Future<Map> take = parseDetailHtmlTaskService.take();
                Map map = take.get();

                HttpClientFactory instance = HttpClientFactory.getInstance();
                map.put("key", "qewqwasdasdsfsqwaawxcfgcegfcd");
                String s = JSON.toJSONString(map);
                String s1 = instance.postData("http://localhost:8080/addVod", s);
                System.out.println(s1);

            } catch (Exception e) {
                e.printStackTrace();
            }


        }

        pool.shutdown();
    }


    //============================================================================================================


    private class ParseHtmlTask implements Callable<List<String>> {

        List hrefList = new LinkedList<String>();
        private String htmlData;

        public ParseHtmlTask(String htmlData) {
            this.htmlData = htmlData;
        }

        @Override
        public List<String> call() throws Exception {
            System.out.println(htmlData);
            ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(htmlData.getBytes("GB2312"));
            Document document = Jsoup.parse(tInputStringStream, "UTF-8", baseUrl);
            Elements ulinks = document.getElementsByClass("ulink");

            for (int j = 0; j < ulinks.size(); j++) {
                if (j % 2 == 0) {
                    continue;
                }

                Element element = ulinks.get(j);
                String href = element.attr("href");

                int var = href.lastIndexOf("/");
                String strPublishDate = href.substring(var - 8, var);
                Date publishDate = DateUtils.parseDate(strPublishDate, "yyyyMMdd");
                if(publishDate.compareTo(crawlDate) == 0){
                    hrefList.add(href);
                }
            }
            return hrefList;
        }
    }

    private class GetHtmlTask implements Callable<String> {
        private Map headers;
        private HttpClientFactory instance;
        private String url;

        public GetHtmlTask(Map headers, HttpClientFactory instance, String url) {
            this.headers = headers;
            this.instance = instance;
            this.url = url;
        }

        @Override
        public String call() throws Exception {
            return instance.get(url, headers);
        }
    }


    private class ParseDetailHtmlTask implements Callable<Map> {

        private String htmlData;
        private String url;

        public ParseDetailHtmlTask(String htmlData, String url) {
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

            if (url.contains("/jddy/")) {
                vod.setCountryType(0);
            } else if (url.contains("/oumei/")) {
                vod.setCountryType(1);
            } else if (url.contains("gndy/rihan/")) {
                vod.setCountryType(2);
            } else if (url.contains("/hytv/")) {
                vod.setCountryType(0);
            } else if (url.contains("/oumeitv/")) {
                vod.setCountryType(1);
            } else if (url.contains("/rihantv/")) {
                vod.setCountryType(2);
            }else {
                vod.setCountryType(0);
            }


            Document document = Jsoup.parse(htmlData);
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

            Elements ps = zoom.getElementsByTag("p");
            StringBuilder stringBuilder = new StringBuilder();
            for (Element p : ps) {
                String ownText = p.ownText();

                String[] split = ownText.split("◎");

                for (int i = 1; i < split.length; i++) {
                    if (split[i].contains("主　　演")) {
                        split[i] = split[i].replace(" 　　　　　　", "/");
                    }
                    stringBuilder.append("<br>◎" + split[i]);
                }
            }
            vod.setContent(stringBuilder.toString());

            Elements downloadUrls = zoom.getElementsByTag("a");
            System.out.println("===============================");

            HashSet<String> urls = new HashSet<>();
            for (int i = 0; i < downloadUrls.size(); i++) {
                Element element = downloadUrls.get(i);
                String href = element.attr("href");
                urls.add(href);
            }
            HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
            objectObjectHashMap.put("vod", vod);
            objectObjectHashMap.put("urls", urls);

            return objectObjectHashMap;
        }
    }

}
