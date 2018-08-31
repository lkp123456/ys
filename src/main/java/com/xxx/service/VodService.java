package com.xxx.service;

import com.xxx.dao.DownloadUrlMapper;
import com.xxx.dao.SeriesMapper;
import com.xxx.dao.VodMapper;
import com.xxx.entity.DownloadUrl;
import com.xxx.entity.Vod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Administrator
 * @Date: 2018/8/31 16:26
 * @Description:
 */
@Service
public class VodService {

    @Autowired
    private VodMapper vodMapper;

    @Autowired
    private SeriesMapper seriesMapper;

    @Autowired
    private DownloadUrlMapper downloadUrlMapper;


    public void addVod(String name, String title, String post_url, String content, String screenshot_url, int vod_type,
                      int country_type, String[] downloadUrls, String[] magnetUrls, String[] sourceName) {
        Vod vod = new Vod();
        vod.setContent(content);
        vod.setCountryType(country_type);
        vod.setName(name);
        vod.setTitle(title);
        vod.setPostUrl(post_url);
        vod.setScreenshotUrl(screenshot_url);
        vod.setVodType(vod_type);

        if (downloadUrls.length == 1) {
            DownloadUrl downloadUrlEntity = new DownloadUrl();
            downloadUrlEntity.setDownloadUrl(downloadUrls[0]);
            downloadUrlEntity.setMagnetUrl(magnetUrls[0]);
            downloadUrlEntity.setSourceName(sourceName[0]);
        } else {
            for (int i = 0; i < sourceName.length; i++) {
                DownloadUrl downloadUrlEntity = new DownloadUrl();
                downloadUrlEntity.setSourceName(sourceName[i]);
                if (i < downloadUrls.length) {
                    downloadUrlEntity.setDownloadUrl(downloadUrls[i]);
                }
                if (i < magnetUrls.length) {
                    downloadUrlEntity.setMagnetUrl(magnetUrls[i]);
                }

                downloadUrlMapper.insertSelective(downloadUrlEntity);
            }

        }
    }
}
