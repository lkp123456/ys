package com.xxx.dao;

import com.xxx.entity.Vod;
import com.xxx.entity.VodExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VodMapper {
    int countByExample(VodExample example);

    int deleteByExample(VodExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Vod record);

    int insertSelective(Vod record);

    List<Vod> selectByExampleWithBLOBs(VodExample example);

    List<Vod> selectByExample(VodExample example);

    Vod selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Vod record, @Param("example") VodExample example);

    int updateByExampleWithBLOBs(@Param("record") Vod record, @Param("example") VodExample example);

    int updateByExample(@Param("record") Vod record, @Param("example") VodExample example);

    int updateByPrimaryKeySelective(Vod record);

    int updateByPrimaryKeyWithBLOBs(Vod record);

    int updateByPrimaryKey(Vod record);
}