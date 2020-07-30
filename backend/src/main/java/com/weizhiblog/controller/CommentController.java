package com.weizhiblog.controller;

import com.weizhiblog.bean.Comments;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/*
 * 该类是控制评论的控制器
 *
 * @createTime 07-17 19:46:15
 * @author Touka_
 * @classname com.weizhiblog.controller.CommentController
 * @lastModifiedTime 7月17日   19:46:15
 */
@Log4j2
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 该文章添加评论
     *
     * @param comment 评论记录
     * @return 是否添加成功
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ResponseBean addComments(@RequestBody Comments comment) {
        return commentService.addComment(comment);
    }

    /**
     * 删除文章的某条评论
     *
     * @param id 评论id
     * @return 是否删除成功
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseBean deleteComment(@RequestParam("id") @NotNull Integer id) {
        return commentService.deleteComment(id);
    }

    /**
     * 删除某个文章的所有评论
     *
     * @param aid 文章所属id
     * @return 是否删除成功
     */
    @RequestMapping(value = "/delete/all", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseBean deleteAllCommentsByAid(@RequestParam("aid") @NotNull Integer aid) {
        return commentService.deleteAllCommentsByAid(aid);
    }

    /**
     * 更新某个文章的某条评论
     *
     * @param comment 评论记录
     * @return 是否更新成功
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean updateComment(@RequestBody Comments comment) {
        return commentService.updateComment(comment);
    }

    /**
     * 查看某个文章的所有评论
     *
     * @param aid 文章id
     * @return 获取的评论
     */
    @RequestMapping(value = "/all", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean listAllCommentsByAid(@RequestParam("aid") @NotNull Integer aid) {
        return commentService.listAllCommentsByAid(aid);
    }

    /**
     * 查看某个文章的一级评论
     *
     * @param aid 文章id
     * @return 获取的评论
     */
    @RequestMapping(value = "/all/level1", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean listAllLevel1CommentsByAid(@RequestParam("aid") @NotNull Integer aid) {
        return commentService.listAllLevel1CommentsByAid(aid);
    }

    /**
     * 查看某个文章某条评论的所有子评论
     *
     * @param id 评论id
     * @return 获取的评论
     */
    @RequestMapping(value = "/children", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean listAllChildrenComments(@RequestParam("id") @NotNull Integer id) {
        return commentService.listAllChildrenComments(id);
    }

    /**
     * 查看某个文章某条评论的所有下一级评论
     *
     * @param id 评论id
     * @return 获取的评论
     */
    @RequestMapping(value = "/nextLevel", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean listNextLevelComments(@RequestParam("id") @NotNull Integer id) {
        return commentService.listNextLevelComments(id);
    }

}
