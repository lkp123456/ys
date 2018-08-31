package com.xxx.dao;

import com.xxx.entity.DownloadUrl;
import com.xxx.entity.DownloadUrlExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DownloadUrlMapper {
    int countByExample(DownloadUrlExample example);

    int deleteByExample(DownloadUrlExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DownloadUrl record);

    int insertSelective(DownloadUrl record);

    List<DownloadUrl> selectByExample(DownloadUrlExample example);

    DownloadUrl selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DownloadUrl record, @Param("example") DownloadUrlExample example);

    int updateByExample(@Param("record") DownloadUrl record, @Param("example") DownloadUrlExample example);

    int updateByPrimaryKeySelective(DownloadUrl record);

    int updateByPrimaryKey(DownloadUrl record);
}