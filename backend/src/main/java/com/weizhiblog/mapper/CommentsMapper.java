package com.weizhiblog.mapper;

import com.weizhiblog.bean.Comment;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {

    /**
     * 根据评论id删除评论
     *
     * @param id 评论id
     * @return 删除成功则返回删除的行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入文章的评论
     *
     * @param record 文章评论
     * @return 插入成功则返回插入行数
     */
    int insert(Comment record);

    /**
     * 插入文章评论，如果文章评论存在，则更新该文章评论为 record
     *
     * @param record 文章评论记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Comment record);

    /**
     * 插入文章评论记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章评论记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Comment record);

    /**
     * 插入或更新文章评论记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章评论记录
     * @return 插入成功则返回 1
     */
    int insertSelective(Comment record);


    /**
     * 根据评论id获取文章评论
     *
     * @param id 评论id
     * @return 存在此id对应的文章评论则返回该文章评论
     */
    Comment selectByPrimaryKey(Integer id);

    /**
     * 根据文章的id更新文章评论（不为null的字段）
     *
     * @param record 文章评论记录
     * @return 更新成功则返回更新行数
     */
    int updateByPrimaryKeySelective(Comment record);

    /**
     * 根据文章评论的id更新文章评论
     *
     * @param record 文章评论记录
     * @return 更新成功则返回更新行数
     */
    int updateByPrimaryKey(Comment record);

    /**
     * 批量更新文章评论（根据每个文章的id）
     *
     * @param list 文章评论列表
     * @return 更新成功则返回插入行数
     */
    int updateBatch(List<Comment> list);

    /**
     * 批量更新文章评论记录（根据每个文章的id）
     *
     * @param list 文章评论列表
     * @return 更新成功则返回插入行数
     */
    int updateBatchSelective(List<Comment> list);

    /**
     * 批量插入文章评论
     *
     * @param list 文章评论列表
     * @return 更新成功则返回更新的行数
     */
    int batchInsert(@Param("list") List<Comment> list);
 /* ************************************************************
     以下为自己写的
     *************************************************************/

    /**
     * 该文章的所有评论
     *
     * @param aid 文章id
     * @return 评论列表
     */
    @Select("SELECT * from comments WHERE aid = #{aid}")
    List<Comment> listCommentsByAid(Integer aid);

    /**
     * @param uid
     * @return
     */
    @Delete("DELETE FROM comments WHERE uid = #{uid}")
    int deleteCommentsByUid(Integer uid);

    /**
     * @param pid
     * @return
     */
    @Select("SELECT * FROM comments WHERE parentId = #{pid} ")
    List<Comment> listChildrenCommentsByPid(Integer pid);
}