package com.weizhiblog.controller;

import com.weizhiblog.bean.Comment;
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
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;


    @PostMapping
    public ResponseBean addComments(@RequestBody Comment comment) {
        return commentService.addComment(comment);
    }


    @DeleteMapping("/{id}")
    public ResponseBean deleteComment(@PathVariable("id") @NotNull Integer id) {
        return commentService.deleteComment(id);
    }

    @DeleteMapping("/article/{aid}")
    public ResponseBean deleteAllCommentsByAid(@PathVariable("aid") @NotNull Integer aid) {
        return commentService.deleteAllCommentsByAid(aid);
    }

    @PutMapping("/{id}")
    public ResponseBean putComment(@PathVariable Integer id,
                                     @RequestBody Comment comment) {
        return commentService.putComment(id,comment);
    }

    @PatchMapping("/{id}")
    public ResponseBean patchComment(@PathVariable Integer id,
                                     @RequestBody Comment comment) {
        return commentService.patchComment(id,comment);
    }


    @GetMapping("/article/{aid}")
    public ResponseBean listAllCommentsByAid(@PathVariable("aid") @NotNull Integer aid) {
        return commentService.listAllCommentsByAid(aid);
    }

    @GetMapping("/{id}/level1")
    public ResponseBean listNextLevelComments(@PathVariable("id") @NotNull Integer id) {
        return commentService.listNextLevelComments(id);
    }

}
