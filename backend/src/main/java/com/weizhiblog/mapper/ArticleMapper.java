package com.weizhiblog.mapper;

import com.weizhiblog.bean.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {
    /**
     * 根据id删除文章
     *
     * @param id 文章id
     * @return 删除成功则返回 1
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入文章
     *
     * @param record 文章记录
     * @return 插入成功则返回 1
     */
    int insert(Article record);

    /**
     * 插入文章，如果文章存在，则更新该文章为 record
     *
     * @param record 文章记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Article record);

    /**
     * 插入文章记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Article record);

    /**
     * 插入或更新文章（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章记录
     * @return 插入成功则返回 1
     */
    int insertSelective(Article record);

    /**
     * 根据文章id获取文章
     *
     * @param id 文章id
     * @return 存在此id对应的文章则返回该文章
     */
    Article selectByPrimaryKey(Integer id);

    /**
     * 根据文章的id更新文章（不为null的字段）
     *
     * @param record 文章记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKeySelective(Article record);

    /**
     * 根据文章的id更新文章
     *
     * @param record 文章记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKey(Article record);

    /**
     * 批量更新文章（根据每个文章的id）
     *
     * @param list 文章列表
     * @return 更新成功则返回插入行数
     */
    int updateBatch(List<Article> list);

    /**
     * 批量插入文章
     *
     * @param list 文章列表
     * @return 更新成功则返回更新的行数
     */
    int batchInsert(@Param("list") List<Article> list);
}