package com.xxx.service;

import com.xxx.dao.DownloadUrlMapper;
import com.xxx.entity.DownloadUrl;
import com.xxx.entity.DownloadUrlExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Administrator
 * @Date: 2018/9/4 14:48
 * @Description:
 */
@Service
public class DownloadUrlService {
    @Autowired
    private DownloadUrlMapper downloadUrlMapper;

    public List<DownloadUrl> getDownloadUrls(long vodId){

        DownloadUrlExample downloadUrlExample = new DownloadUrlExample();
        DownloadUrlExample.Criteria criteria = downloadUrlExample.createCriteria().andVodIdEqualTo(vodId);
        List<DownloadUrl> downloadUrls = downloadUrlMapper.selectByExample(downloadUrlExample);
        return downloadUrls;
    }
}
