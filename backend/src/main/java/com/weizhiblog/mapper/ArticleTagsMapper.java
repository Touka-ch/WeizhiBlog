package com.weizhiblog.mapper;

import com.weizhiblog.bean.ArticleTags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleTagsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleTags record);

    int insertOrUpdate(ArticleTags record);

    int insertOrUpdateSelective(ArticleTags record);

    int insertSelective(ArticleTags record);

    ArticleTags selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleTags record);

    int updateByPrimaryKey(ArticleTags record);

    int updateBatch(List<ArticleTags> list);

    int updateBatchSelective(List<ArticleTags> list);

    int batchInsert(@Param("list") List<ArticleTags> list);
}