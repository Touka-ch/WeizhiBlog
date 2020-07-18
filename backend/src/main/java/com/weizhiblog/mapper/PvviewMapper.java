package com.weizhiblog.mapper;

import com.weizhiblog.bean.Pvview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PvviewMapper {
    int insert(Pvview record);

    int insertOrUpdate(Pvview record);

    int insertOrUpdateSelective(Pvview record);

    int insertSelective(Pvview record);

    int batchInsert(@Param("list") List<Pvview> list);
}