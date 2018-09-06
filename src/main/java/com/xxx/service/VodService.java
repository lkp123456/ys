package com.xxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.dao.DownloadUrlMapper;
import com.xxx.dao.SeriesMapper;
import com.xxx.dao.VodMapper;
import com.xxx.entity.DownloadUrl;
import com.xxx.entity.Vod;
import com.xxx.entity.VodExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
                       int country_type, Date publishDate, String[] downloadUrls, String[] magnetUrls, String[] sourceName) {
        Vod vod = new Vod();
        vod.setContent(content);
        vod.setCountryType(country_type);
        vod.setName(name);
        vod.setTitle(title);
        vod.setPostUrl(post_url);
        vod.setScreenshotUrl(screenshot_url);
        vod.setPublishDate(publishDate);
        vod.setVodType(vod_type);

        vodMapper.insertSelective(vod);

        for (int i = 0; i < sourceName.length; i++) {
            DownloadUrl downloadUrlEntity = new DownloadUrl();
            downloadUrlEntity.setSourceName(sourceName[i]);
            if (i < downloadUrls.length) {
                downloadUrlEntity.setDownloadUrl(downloadUrls[i]);
            }
            if (i < magnetUrls.length) {
                downloadUrlEntity.setMagnetUrl(magnetUrls[i]);
            }
            downloadUrlEntity.setVodId(vod.getId());
            downloadUrlMapper.insertSelective(downloadUrlEntity);
        }
    }

    public PageInfo<Vod> getVods(Integer vodType,Integer countryType,int startPage,int pageSize){
        PageHelper.startPage(startPage,pageSize);
        VodExample vodExample = new VodExample();
        VodExample.Criteria criteria = vodExample.createCriteria();
        if(vodType!=null){
            criteria.andVodTypeEqualTo(vodType);
        }
        if(countryType!=null){
            criteria.andCountryTypeEqualTo(countryType);
        }
        vodExample.setOrderByClause("publish_date DESC");
        List<Vod> vods = vodMapper.selectByExample(vodExample);
        PageInfo<Vod> vodPageInfo = new PageInfo<>(vods);
        return vodPageInfo;
    }

    public Vod getVodById(Long id){
        VodExample vodExample = new VodExample();
        VodExample.Criteria criteria = vodExample.createCriteria();
        criteria.andIdEqualTo(id);
        Vod vod = vodMapper.selectByPrimaryKey(id);
        return vod;
    }

    public List<Vod> getVodsByName(String name){
        VodExample vodExample = new VodExample();
        VodExample.Criteria criteria = vodExample.createCriteria();
        criteria.andNameEqualTo(name);
        List<Vod> vods = vodMapper.selectByExample(vodExample);
        return vods;
    }

}
