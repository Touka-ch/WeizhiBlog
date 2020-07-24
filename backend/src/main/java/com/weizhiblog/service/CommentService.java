package com.weizhiblog.service;

/*
 *
 * @createTime 07-17 19:46:24
 * @author Touka_
 * @classname com.weizhiblog.service.CommentService
 * @lastModifiedTime 7月17日   19:46:24
 */

import com.weizhiblog.bean.Comments;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.mapper.CommentsMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class CommentService {
    @Autowired
    CommentsMapper commentsMapper;

    /**
     * 给某个文章添加评论
     * @param id 文章所属id
     * @param  comments 评论记录
     * @return 是否添加成功
     */
    public ResponseBean addComments(Integer id ,Comments comments){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 删除某个文章的某条评论
     * @param id 文章所属id
     * @return 是否删除成功
     */
    public ResponseBean deleteComments(Integer id ){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 删除某个文章的所有评论
     * @param id 文章所属id
     * @return 是否删除成功
     */
    public ResponseBean deleteAllComments(Integer id ){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 更新某个文章的某条评论
     * @param aid 文章id
     * @param id 评论id
     * @param comments 评论记录
     * @return 是否更新成功
     */
    public ResponseBean updateComments(Integer id,Integer aid ,Comments comments){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 查看某个文章的所有评论
     * @param id 文章id
     * @return 获取的评论
     */
    public ResponseBean viewAllComments_article(Integer id){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 查看某个文章的一级评论
     * @param id 文章id
     * @return 获取的评论
     */
    public ResponseBean viewFirstComments(Integer id){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 查看某个文章某条评论的所有子评论
     * @param id 评论id
     * @param aid 文章id
     * @return 获取的评论
     */
    public ResponseBean viewAllComments_comments(Integer id,Integer aid){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 查看某个文章某条评论的所有下一级评论
     * @param id 评论id
     * @param aid 文章id
     * @return 获取的评论
     */
    public ResponseBean viewAllFirstComments_comments(Integer id,Integer aid){
        return ResponseBean.builder().status(1).message("").build();
    }
}
