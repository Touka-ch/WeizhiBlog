package com.weizhiblog.mapper;

import com.weizhiblog.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Article record);

    int insertOrUpdate(Article record);

    int insertOrUpdateSelective(Article record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Article record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Article selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Article record);

    int updateBatch(List<Article> list);

    int updateBatchSelective(List<Article> list);

    int batchInsert(@Param("list") List<Article> list);
}