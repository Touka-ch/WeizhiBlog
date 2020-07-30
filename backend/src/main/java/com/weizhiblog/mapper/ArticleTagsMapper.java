package com.weizhiblog.mapper;

import com.weizhiblog.bean.ArticleTags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleTagsMapper {
    /**
     * 根据id删除文章标签
     *
     * @param id 文章id
     * @return 删除成功则返回 1
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入文章标签
     *
     * @param record 文章标签记录
     * @return 插入成功则返回 1
     */
    int insert(ArticleTags record);

    /**
     * 插入文章标签，如果文章标签存在，则更新该文章标签为 record
     *
     * @param record 文章标签记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(ArticleTags record);

    /**
     * 插入或更新文章标签记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章标签记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(ArticleTags record);

    /**
     * 插入文章标签记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章标签记录
     * @return 插入或更新成功则返回 1
     */
    int insertSelective(ArticleTags record);

    /**
     * 根据文章id获取文章标签
     *
     * @param id 文章id
     * @return 存在此id对应的文章则返回该文章标签
     */
    ArticleTags selectByPrimaryKey(Integer id);

    /**
     * 根据文章id更新文章标签记录（不为null的字段）
     *
     * @param record 文章标签记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKeySelective(ArticleTags record);

    /**
     * 根据文章id更新文章标签
     *
     * @param record 文章标签记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKey(ArticleTags record);

    /**
     * 批量更新文章标签（根据每个文章的id）
     *
     * @param list 文章标签列表
     * @return 更新成功则返回更新行数
     */
    int updateBatch(List<ArticleTags> list);

    /**
     * 批量更新文章标签记录（根据每个文章的id）
     *
     * @param list 文章标签记录
     * @return 更新成功则返回更新行数
     */
    int updateBatchSelective(List<ArticleTags> list);

    /**
     * 批量插入文章标签（根据每个文章的id）
     *
     * @param list 文章标签列表
     * @return 插入成功则返回插入的行数
     */
    int batchInsert(@Param("list") List<ArticleTags> list);

    ArticleTags selectByUidTid(@Param("aid") Integer aid, @Param("tid") Integer tid);

    int deleteByAid(int aid);

    ArticleTags selectByAid(Integer aid);

    /**
     * 根据 标签id找所有的规则
     *
     * @param tid 标签id
     * @return 文章id集合
     */
    @Select("SELECT aid from article_tags WHERE tid = #{tid}")
    List<Integer> listAidsByTid(Integer tid);

    /**
     * 根据aid获取tid集合
     *
     * @param aid 文章id
     * @return 标签id集合
     */
    @Select("SELECT tid from article_tags WHERE aid = #{aid}")
    List<Integer> listTidsByAid(Integer aid);

    /**
     *
     * @param aid
     * @param tid
     * @return
     */
    @Select("SELECT * FROM article_tags WHERE aid = #{aid} AND tid = #{tid}")
    ArticleTags listArticleTagsByAidAndTid(Integer aid,Integer tid);
}