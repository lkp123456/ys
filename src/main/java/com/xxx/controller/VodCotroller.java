package com.xxx.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.xxx.base.CategoryURI;
import com.xxx.entity.DownloadUrl;
import com.xxx.entity.Series;
import com.xxx.entity.Vod;
import com.xxx.service.DownloadUrlService;
import com.xxx.service.SeriesService;
import com.xxx.service.VodService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class VodCotroller {



    @Autowired
    private VodService vodService;
    @Autowired
    private DownloadUrlService downloadUrlService;
    @Autowired
    private SeriesService seriesService;

    @ModelAttribute
    public void header(HttpServletRequest request,Model model) {
        String requestURI = request.getRequestURI();

        model.addAttribute("requestUri", requestURI);
        model.addAttribute("categorys", CategoryURI.values()

        );
    }


    @RequestMapping("/{vodType}/{countryType}.html")
    public String showVod(@PathVariable String vodType, @PathVariable String countryType,
                          @RequestParam(name = "startPage", required = false,defaultValue = "1") int startPage, Model model) {
        String requestUri = "/" + vodType + "/" + countryType + ".html";
        if (!CategoryURI.contains(requestUri)) {
            return "404";
        }
        int innerVodType = 0;
        if (StringUtils.equals(vodType, "mv")) {
            innerVodType = 0;
        }
        if (StringUtils.equals(vodType, "tv")) {
            innerVodType = 1;
        }

        //0国内 1欧美 2日韩
        int innerCountryType = 0;

        if (StringUtils.equals(countryType, "china")) {
            innerCountryType = 0;
        }
        if (StringUtils.equals(countryType, "oumei")) {
            innerCountryType = 1;
        }
        if (StringUtils.equals(countryType, "rihan")) {
            innerCountryType = 2;
        }

        PageInfo<Vod> vods = vodService.getVods(innerVodType, innerCountryType, startPage,20);
        model.addAttribute("lastVods", vods);
        model.addAttribute("requestUri", requestUri);

        return "index";

    }

    @RequestMapping("/index.html")
    public String index(Model model) {
        log.debug("index");
        //最新
        PageInfo<Vod> lastVods = vodService.getVods(null, null, 1, 20);
//        PageInfo<Vod> lastTVs = vodService.getVods(1, null, 1, 20);

//        PageInfo<Series> series = seriesService.getSeries();
//        model.addAttribute("series",series);
//        PageInfo<Vod> chinaVods = vodService.getVods(0, 0, 1, 10);
//        PageInfo<Vod> oumeiVods = vodService.getVods(0, 1, 1, 10);
//        PageInfo<Vod> rihanVods = vodService.getVods(0, 2, 1, 10);
//        PageInfo<Vod> chinaTVs = vodService.getVods(1, 0, 1, 10);
//        PageInfo<Vod> oumeiTVs = vodService.getVods(1, 1, 1, 10);
//        PageInfo<Vod> rihanTVs = vodService.getVods(1, 2, 1, 10);

        model.addAttribute("lastVods",lastVods);
//        model.addAttribute("lastTVs",lastTVs);

//        model.addAttribute("chinaVods",chinaVods);
//        model.addAttribute("oumeiVods",oumeiVods);
//        model.addAttribute("rihanVods",rihanVods);
//        model.addAttribute("chinaTVs",chinaTVs);
//        model.addAttribute("oumeiTVs",oumeiTVs);
//        model.addAttribute("rihanTVs",rihanTVs);

        return "index";
    }

    @RequestMapping("/v/{id}.html")
    public String showVod(@PathVariable long id,Model model){
        Vod vodById = vodService.getVodById(id);
        model.addAttribute("vod",vodById);
        return "detail";
    }

    @RequestMapping("/showDownloadLinks/{id}.html")
    @ResponseBody
    public List<DownloadUrl> showDownloadLinks(@PathVariable(name = "id",required = true) long vodId){
        List<DownloadUrl> downloadUrls = downloadUrlService.getDownloadUrls(vodId);
        return downloadUrls;
    }



    @RequestMapping(value = "/addVod", method = RequestMethod.POST)
    @ResponseBody
    public String addVod(@RequestBody Map map) {
        System.out.println(map);
        JSONObject vod = (JSONObject) map.get("vod");
        String key = (String) map.get("key");
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
                countryType, publishDate, downloadUrls, magnetUrls, sourceNames);
        return "success";
    }

}
