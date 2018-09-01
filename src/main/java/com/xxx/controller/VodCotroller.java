package com.xxx.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxx.entity.Vod;
import com.xxx.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.Map;

@Controller
public class VodCotroller {

    @Autowired
    private VodService vodService;


    @RequestMapping(value = "/addVod", method = RequestMethod.POST)
    @ResponseBody
    public String addVod(@RequestBody Map map) {
        System.out.println(map);
        JSONObject vod =(JSONObject)map.get("vod");
        String key =(String)map.get("key");
        String[] urls = ((JSONArray) map.get("urls")).toArray(new String[]{});

        System.out.println(key);
        if (!"qewqwasdasdsfsqwaawxcfgcegfcd".equals(key)) {
            return "invalid";
        }
        String content = vod.getString("content");
        String name = vod.getString("name");
        String title = vod.getString("title");
        String postUrl = vod.getString("postUrl");
        String screenshotUrl = vod.getString("screenshotUrl");
        Integer countryType = vod.getInteger("countryType");
        Integer vodType = vod.getInteger("vodType");
        Date publishDate = vod.getDate("publishDate");


        LinkedList<String> ftpUrlList = new LinkedList<>();
        LinkedList<String> magnetUrlList = new LinkedList<>();
        LinkedList<String> sourceNamesList = new LinkedList<>();
        sourceNamesList.add(name);

        for (int i = 0; i < urls.length; i++) {
            String url = urls[i];
            if (url.startsWith("magnet:")) {
                ftpUrlList.add(url);
            }
            if (url.startsWith("ftp:")) {
                magnetUrlList.add(url);
            }

        }
        String[] downloadUrls = ftpUrlList.toArray(new String[]{""});
        String[] magnetUrls = magnetUrlList.toArray(new String[]{""});
        String[] sourceNames = sourceNamesList.toArray(new String[]{""});


        vodService.addVod(name, title, postUrl, content, screenshotUrl, vodType,
                countryType, publishDate,downloadUrls, magnetUrls,  sourceNames);
        return "success";
    }

}
