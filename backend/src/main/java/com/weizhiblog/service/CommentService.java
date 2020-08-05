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
import com.weizhiblog.exception.MyRuntimeException;
import com.weizhiblog.mapper.ArticleMapper;
import com.weizhiblog.mapper.CommentsMapper;
import com.weizhiblog.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class CommentService {
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 给某个文章添加评论
     *
     * @param comment 评论记录
     * @return 是否添加成功
     */
    @Transactional
    public ResponseBean addComment(Comments comment) {
        Integer aid = comment.getAid();
        String content = comment.getContent();
        Integer parentId = comment.getParentId();
        Timestamp publishTime = comment.getPublishTime();
        Integer uid = comment.getUid();
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        Article article = articleMapper.selectByPrimaryKey(aid);
        if (article == null) {
            return ResponseBean.builder().status(-3).message("该文章不存在！").object(aid).build();
        }
        if (publishTime == null) {
            comment.setPublishTime(new Timestamp(System.currentTimeMillis()));
        }
        if (content.isEmpty()) {
            return ResponseBean.builder().status(-4).message("评论不能为空！").build();
        }
        if (parentId != -1) {
            Comments parentComment = commentsMapper.selectByPrimaryKey(parentId);
            if (parentComment == null) {
                return ResponseBean.builder().status(-5).message("父评论不存在！").build();
            }
        }
        int i = commentsMapper.insert(comment);
        if (i == 1) {
            articleMapper.updateByPrimaryKeySelective(Article.builder().id(aid).commentNum(article.getCommentNum() + 1).build());
            return ResponseBean.builder().status(1).message("成功").object(comment).build();
        } else {
            return ResponseBean.builder().status(0).message("数据库错误！").build();
        }
    }

    /**
     * 删除某个文章的某条评论
     *
     * @param id id
     * @return 是否删除成功
     */
    @Transactional
    public ResponseBean deleteComment(Integer id) {
        Comments comment = commentsMapper.selectByPrimaryKey(id);
        if (comment == null) {
            return ResponseBean.builder().status(-2).message("该评论不存在").object(id).build();
        }
        Article article = articleMapper.selectByPrimaryKey(comment.getAid());
        List<Comments> res = new ArrayList<>();
        res.add(comment);
        ResponseBean responseBean = listNextLevelComments(id);
        if (responseBean.getStatus() == 1) {
            List<Comments> comments = (List<Comments>) responseBean.getObject();
            for (Comments comment1 : comments) {
                res.add(comment1);
                deleteComment(comment1.getId());
            }
        }
        int i = commentsMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            articleMapper.updateByPrimaryKeySelective(Article.builder().id(article.getId()).commentNum(article.getCommentNum() - 1).build());
            return ResponseBean.builder().status(1).message("成功").object(res).build();
        } else {
            return ResponseBean.builder().status(0).message("数据库错误！").build();
        }
    }

    /**
     * 删除某个文章的所有评论
     *
     * @param aid 文章所属id
     * @return 是否删除成功
     */
    @Transactional
    public ResponseBean deleteAllCommentsByAid(Integer aid) {
        if (articleMapper.selectByPrimaryKey(aid) == null) {
            return ResponseBean.builder().status(-2).message("该文章不存在！").object(aid).build();
        }
        List<Comments> comments = commentsMapper.listCommentsByAid(aid);
        if (comments == null || comments.size() == 0) {
            return ResponseBean.builder().status(-3).message("评论列表为空！").build();
        }
        List<Comments> res = new ArrayList<>(comments);
        for (Comments comment : comments) {
            ResponseBean responseBean = deleteComment(comment.getId());
            if (responseBean.getStatus() != 1) {
                throw new MyRuntimeException(responseBean);
            }
            res.addAll((Collection<? extends Comments>) responseBean.getObject());
        }
        articleMapper.updateByPrimaryKeySelective(Article.builder().id(aid).commentNum(0).build());
        return ResponseBean.builder().status(1).message("删除成功！").object(comments).build();
    }


    public ResponseBean putComment(Integer id, Comments comment) {
        Comments comments1 = commentsMapper.selectByPrimaryKey(id);
        if (comments1 == null) {
            return ResponseBean.builder().status(-2).message("该评论不存在！").object(id).build();
        }
        Comments comments = commentsMapper.selectByPrimaryKey(id);
        return commentsMapper.updateByPrimaryKey(comment) == 1 ?
                ResponseBean.builder().status(1).message("更新成功！").object(comments).build() :
                ResponseBean.builder().status(0).message("数据库错误！").build();
    }

    public ResponseBean patchComment(Integer id, Comments comment) {
        Comments comments1 = commentsMapper.selectByPrimaryKey(id);
        comment.setId(id);
        if (comments1 == null) {
            return ResponseBean.builder().status(-2).message("该评论不存在！").object(id).build();
        }
        commentsMapper.updateByPrimaryKeySelective(comment);
        return ResponseBean.builder().status(1).message("更新成功！").object(commentsMapper.selectByPrimaryKey(id)).build();
    }

    public ResponseBean listAllCommentsByAid(Integer aid) {
        if (articleMapper.selectByPrimaryKey(aid) == null) {
            return ResponseBean.builder().status(-2).message("文章不存在").object(aid).build();
        }
        List<Comments> comments = commentsMapper.listCommentsByAid(aid);
        return comments == null || comments.size() == 0 ?
                ResponseBean.builder().status(0).message("该文章没有评论").object(aid).build() :
                ResponseBean.builder().status(1).message("获取成功").object(comments).build();
    }

    public ResponseBean listNextLevelComments(Integer pid) {
        if (commentsMapper.selectByPrimaryKey(pid) == null) {
            return ResponseBean.builder().status(-2).message("评论不存在").build();
        }
        List<Comments> comments = commentsMapper.listChildrenCommentsByPid(pid);
        if (comments == null || comments.size() == 0) {
            return ResponseBean.builder().status(-3).message("没有子评论").build();
        }
        return ResponseBean.builder().status(1).message("获取成功").object(comments).build();
    }

}
