package com.weizhiblog.mapper;

import com.weizhiblog.bean.Comments;
import com.weizhiblog.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentsMapper {

    /**
     * 根据评论id删除评论
     * @param id 评论id
     * @return 删除成功则返回删除的行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入文章的评论
     * @param record 文章评论
     * @return 插入成功则返回插入行数
     */
    int insert(Comments record);

    /**
     * 插入文章评论，如果文章评论存在，则更新该文章评论为 record
     *
     * @param record 文章评论记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Comments record);

    /**
     * 插入文章评论记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章评论记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Comments record);

    /**
     * 插入或更新文章评论记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章评论记录
     * @return 插入成功则返回 1
     */
    int insertSelective(Comments record);


    /**
     * 根据评论id获取文章评论
     *
     * @param id 评论id
     * @return 存在此id对应的文章评论则返回该文章评论
     */
    Comments selectByPrimaryKey(Integer id);

    /**
     * 根据文章的id更新文章评论（不为null的字段）
     *
     * @param record 文章评论记录
     * @return 更新成功则返回更新行数
     */
    int updateByPrimaryKeySelective(Comments record);

    /**
     * 根据文章评论的id更新文章评论
     *
     * @param record 文章评论记录
     * @return 更新成功则返回更新行数
     */
    int updateByPrimaryKey(Comments record);

    /**
     * 批量更新文章评论（根据每个文章的id）
     *
     * @param list 文章评论列表
     * @return 更新成功则返回插入行数
     */
    int updateBatch(List<Comments> list);

    /**
     * 批量更新文章评论记录（根据每个文章的id）
     *
     * @param list 文章评论列表
     * @return 更新成功则返回插入行数
     */
    int updateBatchSelective(List<Comments> list);

    /**
     * 批量插入文章评论
     *
     * @param list 文章评论列表
     * @return 更新成功则返回更新的行数
     */
    int batchInsert(@Param("list") List<Comments> list);
 /* ************************************************************
     以下为自己写的
     *************************************************************/

    /**
     * 根据文章id获取评论
     * @param aid 文章id
     * @return 获取成功则返回1
     */
 List<Comments> ListCommentsByAid(Integer aid);

    /**
     * 通过文章id删除所有评论
     * @param aid 文章id
     * @return 获取成功则返回1
     */
    int deleteAllByAid(Integer aid);
    /**
     * 根据文章id获取一级评论
     * @param aid 文章id
     * @param parentId 评论级数(值为-1)
     * @return 删除成功则返回1
     */
    List<Comments> ListFirstCommentsByAid(@Param("aid")Integer aid,@Param("parentId")Integer parentId);
    /**
     * 根据评论id获取一级评论
     * @param id 评论id
     * @param aid 文章aid
     * @param parentId 评论级数
     * @return 获取成功则返回1
     */
    List<Comments> ListCommentsByAid_comments(@Param("id")Integer id,@Param("aid")Integer aid,@Param("parentId")Integer parentId);
    /**
     * 根据文评论id获取一级评论
     * @param id 评论id
     * @param aid 文章id
     * @param parentId 评论级数(值-1)
     * @return 获取成功则返回1
     */
    List<Comments> ListFirstCommentsByAid_comments(@Param("id")Integer id,@Param("aid")Integer aid,@Param("parentId")Integer parentId);
}