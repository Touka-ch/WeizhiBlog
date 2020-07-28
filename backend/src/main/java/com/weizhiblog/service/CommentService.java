package com.weizhiblog.service;

/*
 *
 * @createTime 07-17 19:46:24
 * @author Touka_
 * @classname com.weizhiblog.service.CommentService
 * @lastModifiedTime 7月17日   19:46:24
 */

import com.weizhiblog.bean.Article;
import com.weizhiblog.bean.Comments;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.User;
import com.weizhiblog.mapper.ArticleMapper;
import com.weizhiblog.mapper.CommentsMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class CommentService {
    @Autowired
    CommentsMapper commentsMapper;
    ArticleMapper articleMapper;

    /**
     * 给某个文章添加评论
     * @param id 文章所属id
     * @param  comments 评论记录
     * @return 是否添加成功
     */
    public ResponseBean addComments(Integer id ,Comments comments){
        try{
            if (commentsMapper.selectByPrimaryKey(comments.getId())!=null) {
                return ResponseBean.builder().status(-2).message("标签id已存在！").build();
            }
            if(articleMapper.selectByPrimaryKey(id)==null) {
                return ResponseBean.builder().status(-3).message("该文章不存在！").build();
            }
            return commentsMapper.insert(comments)==1?
                    ResponseBean.builder().status(1).message("添加成功！").build():
                    ResponseBean.builder().status(0).message("未知原因！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }

    }

    /**
     * 删除某个文章的某条评论
     * * @param id 评论所属id
     * @param id 文章所属aid
     * @return 是否删除成功
     */
    public ResponseBean deleteComments(Integer id ,Integer aid){
        try{
            if(commentsMapper.selectByPrimaryKey(id)==null) {
                return ResponseBean.builder().status(-2).message("该评论不存在！").build();
            }
            if(articleMapper.selectByPrimaryKey(id)==null) {
                return ResponseBean.builder().status(-3).message("该文章不存在！").build();
            }
            return commentsMapper.deleteByPrimaryKey(id)==1?
                   ResponseBean.builder().status(1).message("删除成功！").build():
                   ResponseBean.builder().status(0).message("未知原因！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }

    }

    /**
     * 删除某个文章的所有评论
     * @param id 文章所属id
     * @return 是否删除成功
     */
    public ResponseBean deleteAllComments(Integer id ){
        try{

            if(commentsMapper.ListCommentsByAid(id)==null){
                return ResponseBean.builder().status(-2).message("文章无评论").build();
            }
            if(articleMapper.selectByPrimaryKey(id)==null) {
                return ResponseBean.builder().status(-3).message("文章不存在").build();
            }
            return commentsMapper.deleteAllByAid(id)==1?
                    ResponseBean.builder().status(1).message("删除成功").build():
                    ResponseBean.builder().status(0).message("未知原因！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }

    }

    /**
     * 更新某个文章的某条评论(只更新内容）
     * @param aid 文章id
     * @param id 评论id
     * @param comments 评论记录
     * @return 是否更新成功
     */
    public ResponseBean updateComments(Integer id,Integer aid ,Comments comments) {
        try {
            if (commentsMapper.selectByPrimaryKey(id) == null) {
                return ResponseBean.builder().status(-2).message("该评论不存在！").build();
            }
            if (articleMapper.selectByPrimaryKey(aid) == null) {
                return ResponseBean.builder().status(-3).message("文章不存在").build();
            }
            if (comments.getContent() == null) {
                return ResponseBean.builder().status(-4).message("更新评论内容为空").build();
            }
            return commentsMapper.updateByPrimaryKey(comments) == 1 ?
                    ResponseBean.builder().status(1).message("更新成功！").build() :
                    ResponseBean.builder().status(0).message("未知错误！").build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 查看某个文章的所有评论
     * @param id 文章id
     * @return 获取的评论
     */
    public ResponseBean viewAllComments_article(Integer id){
        try {
            List<Comments> comments_1=commentsMapper.ListCommentsByAid(id);
            if (comments_1 == null) {
                return ResponseBean.builder().status(-2).message("文章无评论").build();
            }
            Article article_1=articleMapper.selectByPrimaryKey(id);
            if (article_1 == null) {
                return ResponseBean.builder().status(-3).message("文章不存在").build();
            }
            return commentsMapper.ListCommentsByAid(id).size() == 0 ?
                    ResponseBean.builder().status(-2).message("评论数量为空！").build() :
                    ResponseBean.builder().status(1).message("获取成功！").build();

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 查看某个文章的一级评论
     * @param id 文章id
     * @return 获取的评论
     */
    public ResponseBean viewFirstComments(Integer id){
        try{
            if (commentsMapper.ListCommentsByAid(id) == null) {
                return ResponseBean.builder().status(-2).message("文章无评论").build();
            }

            if (articleMapper.selectByPrimaryKey(id) == null) {
                return ResponseBean.builder().status(-3).message("文章不存在").build();
            }
            return commentsMapper.ListFirstCommentsByAid(id).size() == 0 ?
                    ResponseBean.builder().status(-2).message("评论数量为空！").build() :
                    ResponseBean.builder().status(1).message("获取成功！").object(commentsMapper.ListCommentsByAid(id)).build();

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }

    }

    /**
     * 查看某个文章某条评论的所有子评论
     * @param id 评论id
     * @param aid 文章id
     * @return 获取的评论
     */
    public ResponseBean viewAllComments_comments(Integer id,Integer aid){
        try{
            if (commentsMapper.ListCommentsByAid(id) == null) {
                return ResponseBean.builder().status(-2).message("文章无评论").build();
            }

            if (articleMapper.selectByPrimaryKey(id) == null) {
                return ResponseBean.builder().status(-3).message("文章不存在").build();
            }
            return commentsMapper.ListCommentsByAid_comments(id,aid).size()==0?
                    ResponseBean.builder().status(-2).message("评论数量为空！").build() :
                    ResponseBean.builder().status(1).message("获取成功！").build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 查看某个文章某条评论的所有下一级评论
     * @param id 评论id
     * @param aid 文章id
     * @return 获取的评论
     */
    public ResponseBean viewAllFirstComments_comments(Integer id,Integer aid){
        try{
            if (commentsMapper.ListCommentsByAid(id) == null) {
                return ResponseBean.builder().status(-2).message("文章无评论").build();
            }

            if (articleMapper.selectByPrimaryKey(id) == null) {
                return ResponseBean.builder().status(-3).message("文章不存在").build();
            }
            return commentsMapper.ListFirstCommentsByAid_comments(id,aid).size() == 0 ?
                    ResponseBean.builder().status(-2).message("评论数量为空！").build() :
                    ResponseBean.builder().status(1).message("获取成功！").object(commentsMapper.ListCommentsByAid(id)).build();

        }catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }

    }
}
