package com.xxx.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xxx.dao.DownloadUrlMapper;
import com.xxx.dao.SeriesMapper;
import com.xxx.entity.DownloadUrl;
import com.xxx.entity.DownloadUrlExample;
import com.xxx.entity.Series;
import com.xxx.entity.SeriesExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/4 14:48
 * @Description:
 */
@Service
public class SeriesService {
    @Autowired
    private SeriesMapper seriesMapper;

    public PageInfo<Series> getSeries(){
        PageHelper.startPage(1,20);
        SeriesExample seriesExample = new SeriesExample();
        seriesExample.setOrderByClause("create_time DESC");
        List<Series> series = seriesMapper.selectByExample(seriesExample);
        PageInfo<Series> seriesPageInfo = new PageInfo<>(series);
        return seriesPageInfo;
    }
}
