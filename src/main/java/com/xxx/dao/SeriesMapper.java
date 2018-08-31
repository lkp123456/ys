package com.xxx.dao;

import com.xxx.entity.Series;
import com.xxx.entity.SeriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeriesMapper {
    int countByExample(SeriesExample example);

    int deleteByExample(SeriesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Series record);

    int insertSelective(Series record);

    List<Series> selectByExample(SeriesExample example);

    Series selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Series record, @Param("example") SeriesExample example);

    int updateByExample(@Param("record") Series record, @Param("example") SeriesExample example);

    int updateByPrimaryKeySelective(Series record);

    int updateByPrimaryKey(Series record);
}