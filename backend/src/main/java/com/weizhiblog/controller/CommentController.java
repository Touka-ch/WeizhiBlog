package com.weizhiblog.controller;

import com.weizhiblog.bean.Comments;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.CommentService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * 该文章添加评论
     * @param id 文章所属id
     * @param  comments 评论记录
     * @return 是否添加成功
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ResponseBean CommentsAdd(@RequestParam("id") @NotNull Integer id ,
                                    @RequestBody Comments comments) {
        return commentService.addComments(id,comments);
    }
    /**
     * 删除文章的某条评论
     * @param id 评论所属id
     * @param aid 文章所属aid
     * @return 是否删除成功
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.DELETE})
    public ResponseBean CommentsDelete( @RequestParam("id") @NotNull Integer id,
                                        @RequestParam("aid") @NotNull Integer aid){
        return commentService.deleteComments(id,aid);
    }
    /**
     * 删除某个文章的所有评论
     * @param id 文章所属id
     * @return 是否删除成功
     */
    @RequestMapping(value = "/delete/all", method = {RequestMethod.POST,RequestMethod.DELETE})
    public ResponseBean AllCommentsDelete(@RequestParam("id") @NotNull Integer id ){
        return commentService.deleteAllComments(id);
    }
    /**
     * 更新某个文章的某条评论
     * @param aid 文章aid
     * @param id 评论id
     * @param comments 评论记录
     * @return 是否更新成功
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.PUT})
    public ResponseBean CommentsUpdate(@RequestParam("id") @NotNull Integer id,
                                       @RequestParam("aid") @NotNull Integer aid ,Comments comments){
        return commentService.updateComments(id,aid,comments);
    }
    /**
     * 查看某个文章的所有评论
     * @param id 文章id
     * @return 获取的评论
     */
    @RequestMapping(value = "/all/article", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean AllCommentsView_article(@RequestParam("id") @NotNull Integer id){
        return commentService.viewAllComments_article(id);
    }
    /**
     * 查看某个文章的一级评论
     * @param id 文章id
     * @return 获取的评论
     */
    @RequestMapping(value = "/all/First_article", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean FirstCommentsView(@RequestParam("id") @NotNull Integer id,
                                          @RequestParam("parentId") @NotNull Integer parentId){
        return commentService.viewFirstComments(id,parentId);
    }
    /**
     * 查看某个文章某条评论的所有子评论
     * @param id 评论id
     * @param aid 文章id
     * @return 获取的评论
     */
    @RequestMapping(value = "/all/comments", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean AllCommentsView_comments(@RequestParam("id") @NotNull Integer id,
                                                 @RequestParam("aid") @NotNull Integer aid,
                                                 @RequestParam("parentId") @NotNull Integer parentId){
        return commentService.viewAllComments_comments(id,aid,parentId);
    }
    /**
     * 查看某个文章某条评论的所有下一级评论
     * @param id 评论id
     * @param aid 文章id
     * @return 获取的评论
     */
    @RequestMapping(value = "/all/First_comments", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean AllFirstCommentsView_comments(@RequestParam("id") @NotNull Integer id,
                                                      @RequestParam("aid") @NotNull Integer aid,
                                                      @RequestParam("parentId") @NotNull Integer parentId){
        return commentService.viewAllFirstComments_comments(id,aid,parentId);
    }

}
