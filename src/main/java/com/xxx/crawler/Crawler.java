package com.xxx.crawler;

import com.xxx.utils.HttpClientFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;
import java.util.concurrent.*;


/**
 * @Auther: Administrator
 * @Date: 2018/8/13 11:19
 * @Description:
 */
public class Crawler {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5, 20, 200, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        HashMap<String, String> sgHeaders = new HashMap<>();
        sgHeaders.put("Cookie", "ABTEST=8|1534131639|v1; SNUID=47FB8D9FF4F186BC2F9611E1F52FB2F8; IPLOC=CN1100; SUID=B20F786A2930990A000000005B70FDB7; SUID=B20F786A541C940A000000005B70FDB7; SUV=002F259F6A780FB25B70FDB7B94BE659; JSESSIONID=aaaR8jytCGYqOhhuyo7tw; ppinf=5|1534131700|1535341300|dHJ1c3Q6MToxfGNsaWVudGlkOjQ6MjAxN3x1bmlxbmFtZTozNjolRTYlOTYlQjclRTQlQkElODYlRTclOUElODQlRTUlQkMlQTZ8Y3J0OjEwOjE1MzQxMzE3MDB8cmVmbmljazozNjolRTYlOTYlQjclRTQlQkElODYlRTclOUElODQlRTUlQkMlQTZ8dXNlcmlkOjQ0Om85dDJsdUM2WG4zSmE3TkR5LTkzbGRTLXdqbEVAd2VpeGluLnNvaHUuY29tfA; pprdig=f8rlbhAEfZsH9DC8ASXMmut5PYqa-ioosWDjXO_IUlLGoqGIA9giABsWuqwF5LnsRSXjm_yi_Z5rVHlyvH1lm_8EzJYQwLVKV1zSt4RNoW8FD65YlBp4yIq6nNmVRwpH1avj5xSLWS1zQYKghqa-w4Bx0_VnMSnoZuUvaG7d6xE; sgid=15-36584351-AVtwicfRpP0sr5gAaFibnNJY4; td_cookie=674456516; weixinIndexVisited=1; pgv_pvi=8114589696; pgv_si=s2941038592; ppmdig=1534210489000000dae6c32b91197fb0f2d2394e972fe4e4; sct=4");
        sgHeaders.put("Referer", "http://weixin.sogou.com/weixin?type=2&s_from=input&query=Leyou517&ie=utf8&_sug_=n&_sug_type_=");
        sgHeaders.put("Upgrade-Insecure-Requests", "1");
        sgHeaders.put("Pragma", "no-cache");
        sgHeaders.put("Host", "weixin.sogou.com");
        sgHeaders.put("Connection", "keep-alive");
        sgHeaders.put("Cache-Control", "no-cache");
        sgHeaders.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        sgHeaders.put("Accept-Encoding", "gzip, deflate, sdch");
        sgHeaders.put("Accept-Language", "zh-CN,zh;q=0.8");


        CompletionService<String> objectCompletionService = new ExecutorCompletionService<>(pool);
        int taskSize = 1;
        int page = 0;
        for (int i = 0; i < taskSize; i++) {
            page++;
            String url = "http://weixin.sogou.com/weixin?query=Leyou517&_sug_type_=&s_from=input&_sug_=n&type=2&page=" + page + "&ie=utf8";
            HttpClientFactory instance = HttpClientFactory.getInstance();
            Crawler.GetHtmlTask getHtmlTask = new Crawler().new GetHtmlTask(sgHeaders, instance, url);
            Thread.sleep(2000);
            objectCompletionService.submit(getHtmlTask);
            System.out.println("submit task size = " + i);
        }

        CompletionService<List<String>> parsetCompletionService = new ExecutorCompletionService<>(pool);
        for (int i = 0; i < taskSize; i++) {
            Future<String> future = objectCompletionService.take();
            String s = future.get();
            System.out.println(s);
            System.out.println(i);

            Crawler.ParseHtmlTask parseHtmlTask = new Crawler().new ParseHtmlTask(s);
            parsetCompletionService.submit(parseHtmlTask);

        }

        LinkedHashSet<String> urls = new LinkedHashSet<>();

        for (int i = 0; i < taskSize; i++) {
            Future<List<String>> future = parsetCompletionService.take();
            List<String> taskUrls = future.get();
            urls.addAll(taskUrls);
        }
        for (String url : urls) {
            System.out.println(url);
        }
        pool.shutdown();

//        HashMap<String, String> wxHeaders = new HashMap<>();
//        wxHeaders.put("Cookie", "ABTEST=8|1534131639|v1; SNUID=47FB8D9FF4F186BC2F9611E1F52FB2F8; IPLOC=CN1100; SUID=B20F786A2930990A000000005B70FDB7; SUID=B20F786A541C940A000000005B70FDB7; SUV=002F259F6A780FB25B70FDB7B94BE659; JSESSIONID=aaaR8jytCGYqOhhuyo7tw; ppinf=5|1534131700|1535341300|dHJ1c3Q6MToxfGNsaWVudGlkOjQ6MjAxN3x1bmlxbmFtZTozNjolRTYlOTYlQjclRTQlQkElODYlRTclOUElODQlRTUlQkMlQTZ8Y3J0OjEwOjE1MzQxMzE3MDB8cmVmbmljazozNjolRTYlOTYlQjclRTQlQkElODYlRTclOUElODQlRTUlQkMlQTZ8dXNlcmlkOjQ0Om85dDJsdUM2WG4zSmE3TkR5LTkzbGRTLXdqbEVAd2VpeGluLnNvaHUuY29tfA; pprdig=f8rlbhAEfZsH9DC8ASXMmut5PYqa-ioosWDjXO_IUlLGoqGIA9giABsWuqwF5LnsRSXjm_yi_Z5rVHlyvH1lm_8EzJYQwLVKV1zSt4RNoW8FD65YlBp4yIq6nNmVRwpH1avj5xSLWS1zQYKghqa-w4Bx0_VnMSnoZuUvaG7d6xE; sgid=15-36584351-AVtwicfRpP0sr5gAaFibnNJY4; td_cookie=674456516; weixinIndexVisited=1; pgv_pvi=8114589696; pgv_si=s2941038592; ppmdig=1534210489000000dae6c32b91197fb0f2d2394e972fe4e4; sct=4");
//        wxHeaders.put("Referer", "http://weixin.sogou.com/weixin?type=2&s_from=input&query=Leyou517&ie=utf8&_sug_=n&_sug_type_=");
//        wxHeaders.put("Upgrade-Insecure-Requests", "1");
//        wxHeaders.put("Pragma", "no-cache");
//        wxHeaders.put("Host", "weixin.sogou.com");
//        wxHeaders.put("Connection", "keep-alive");
//        wxHeaders.put("Cache-Control", "no-cache");
//        wxHeaders.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
//        wxHeaders.put("Accept-Encoding", "gzip, deflate, sdch");
//        wxHeaders.put("Accept-Language", "zh-CN,zh;q=0.8");
//        for (String url: urls) {
//            HttpClientFactory instance = HttpClientFactory.getInstance();
//            Crawler.GetHtmlTask getHtmlTask = new Crawler().new GetHtmlTask(wxHeaders, instance, url);
//            Thread.sleep(2000);
//            objectCompletionService.submit(getHtmlTask);
//        }


    }

    private class ParseHtmlTask implements Callable<List<String>> {

        List hrefList = new LinkedList<String>();
        private String htmlData;

        public ParseHtmlTask(String htmlData) {
            this.htmlData = htmlData;
        }

        @Override
        public List<String> call() throws Exception {
            Document document = Jsoup.parse(htmlData);
            Elements elementsByAttributeValue = document.getElementsByAttributeValue("target", "_blank");
            for (Element element : elementsByAttributeValue) {
                String href = element.attr("href");
                if (href.startsWith("http://mp.weixin.qq.com/s?")) {
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


}
