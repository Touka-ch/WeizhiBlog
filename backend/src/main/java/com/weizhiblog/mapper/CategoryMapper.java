package com.weizhiblog.mapper;

import com.weizhiblog.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertOrUpdate(Category record);

    int insertOrUpdateSelective(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    int updateBatch(List<Category> list);

    int updateBatchSelective(List<Category> list);

    int batchInsert(@Param("list") List<Category> list);
}