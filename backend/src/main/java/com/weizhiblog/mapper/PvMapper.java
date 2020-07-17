package com.weizhiblog.mapper;

import com.weizhiblog.bean.Pv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PvMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Pv record);

    int insertOrUpdate(Pv record);

    int insertOrUpdateSelective(Pv record);

    int insertSelective(Pv record);

    Pv selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Pv record);

    int updateByPrimaryKey(Pv record);

    int updateBatch(List<Pv> list);

    int updateBatchSelective(List<Pv> list);

    int batchInsert(@Param("list") List<Pv> list);
}