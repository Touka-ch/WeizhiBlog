package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 *
 * @createTime 07-22 12:44:15
 * @author Touka_
 * @classname com.weizhiblog.controller.DataController
 * @lastModifiedTime 7月22日   12:44:15
 */
@RestController
@RequestMapping("/data")
public class DataController {
    @Autowired
    DataService dataService;

    /**
     * 获取某用户文章的总浏览量
     *
     * @param uid 用户id
     * @return ResponseBean
     */
    @GetMapping("/pv/{uid}")
    public ResponseBean getPvNumByUserId(@PathVariable("uid") Integer uid) {
        return dataService.getPvNumByUserId(uid);
    }

    /**
     * 获取某用户文章的总点赞数
     *
     * @param uid 用户id
     * @return ResponseBean
     */
    @GetMapping("/like/{uid}")
    public ResponseBean getLikeNumByUserId(@PathVariable("uid") Integer uid) {
        return dataService.getLikeNumByUserId(uid);
    }

    /**
     * 获取某用户文章的总评论数
     *
     * @param uid 用户id
     * @return ResponseBean
     */
    @GetMapping("/comment/{uid}")
    public ResponseBean getCommentNumByUserId(@PathVariable("uid") Integer uid) {
        return dataService.getCommentNumByUserId(uid);
    }

    /**
     * 获取某用户文章在前n天每天的浏览量（是一个List<Integer>）
     *
     * @param uid 用户id
     * @param n   天数
     * @return ResponseBean
     */
    @GetMapping("/pv/{uid}/{n}")
    public ResponseBean getPvNumInNDayByUserId(@PathVariable("uid") Integer uid,
                                               @PathVariable("n") Integer n) {
        return dataService.getPvNumInNDayByUserId(uid, n);
    }

    /**
     * 获取某用户文章在前n天每天的点赞数（是一个List<Integer>）
     *
     * @param uid 用户id
     * @param n   天数
     * @return ResponseBean
     */
    @GetMapping("like/{uid}/{n}")
    public ResponseBean getLikeNumInNDayByUserId(@PathVariable("uid") Integer uid,
                                                 @PathVariable("n") Integer n) {
        return dataService.getLikeNumInNDayByUserId(uid, n);
    }

    /**
     * 获取某用户文章在前n天每天的评论数（是一个List<Integer>）
     *
     * @param uid 用户id
     * @param n   天数
     * @return ResponseBean
     */
    @GetMapping("comment/{uid}/{n}")
    public ResponseBean getCommentNumInNDayByUserId(@PathVariable("uid") Integer uid,
                                                    @PathVariable("n") Integer n) {
        return dataService.getCommentNumInNDayByUserId(uid, n);
    }

}
