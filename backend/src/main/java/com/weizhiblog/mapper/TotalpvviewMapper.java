package com.weizhiblog.mapper;

import com.weizhiblog.bean.Totalpvview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TotalpvviewMapper {
    int insert(Totalpvview record);

    int insertOrUpdate(Totalpvview record);

    int insertOrUpdateSelective(Totalpvview record);

    int insertSelective(Totalpvview record);

    int batchInsert(@Param("list") List<Totalpvview> list);
}