package com.xxx.controller;

import com.xxx.utils.HttpClientFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

/**
 * @Auther: Administrator
 * @Date: 2018/9/10 16:26
 * @Description:
 */
@Controller
public class GoogleController {


    @RequestMapping(value = "/g")
    public void request(HttpServletRequest request, HttpServletResponse response) {

        try {
            HttpClientFactory instance = HttpClientFactory.getInstance();
            //获取所有的消息头名称
            Enumeration<String> headerNames = request.getHeaderNames();
            //获取获取的消息头名称，获取对应的值，并输出
            HashMap<String, String> headers = new HashMap<>();
            while (headerNames.hasMoreElements()) {
                String headerKey = headerNames.nextElement();

                String headerValue = request.getHeader(headerKey);
                if (headerKey.equals("host") || headerKey.equals("Referer")) {
                    continue;
                }
                headers.put(headerKey, headerValue);
            }
            String substring = request.getRequestURI().substring(2);
            String s = instance.get("https://www.baidu.com"+substring, headers);

            Document parse = Jsoup.parse(s,"www.baidu.com");
            Elements a = parse.getElementsByTag("a");
            for (Element element : a) {
                String href = element.attr("href");
                if(href.startsWith("https://www.baidu.com")){
                    String replace = href.replace("https://www.baidu.com", "localhost:8080/g");
                    element.attr("href",replace);
                }
            }


            String s2 = parse.html();
            PrintWriter writer = response.getWriter();
            writer.write(s2);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
