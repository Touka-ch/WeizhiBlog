package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @RequestMapping(value = "/pv", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getPvNumByUserId(@RequestParam("uid") Integer uid) {
        return dataService.getPvNumByUserId(uid);
    }

    /**
     * 获取某用户文章的总点赞数
     *
     * @param uid 用户id
     * @return ResponseBean
     */
    @RequestMapping(value = "/star", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getLikeNumByUserId(@RequestParam("uid") Integer uid) {
        return dataService.getLikeNumByUserId(uid);
    }

    /**
     * 获取某用户文章的总评论数
     *
     * @param uid 用户id
     * @return ResponseBean
     */
    @RequestMapping(value = "/comment", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getCommentNumByUserId(@RequestParam("uid") Integer uid) {
        return dataService.getCommentNumByUserId(uid);
    }

    /**
     * 获取某用户文章在前n天每天的浏览量（是一个List<Integer>）
     *
     * @param uid 用户id
     * @param n   天数
     * @return ResponseBean
     */
    @RequestMapping(value = "/pvNDay", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getPvNumInNDayByUserId(@RequestParam("uid") Integer uid,
                                               @RequestParam("n") Integer n) {
        return dataService.getPvNumInNDayByUserId(uid, n);
    }

    /**
     * 获取某用户文章在前n天每天的点赞数（是一个List<Integer>）
     *
     * @param uid 用户id
     * @param n   天数
     * @return ResponseBean
     */
    @RequestMapping(value = "/starNDay", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getLikeNumInNDayByUserId(@RequestParam("uid") Integer uid,
                                                 @RequestParam("n") Integer n) {
        return dataService.getLikeNumInNDayByUserId(uid, n);
    }

    /**
     * 获取某用户文章在前n天每天的评论数（是一个List<Integer>）
     *
     * @param uid 用户id
     * @param n   天数
     * @return ResponseBean
     */
    @RequestMapping(value = "/commentNDay", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getCommentNumInNDayByUserId(@RequestParam("uid") Integer uid,
                                                    @RequestParam("n") Integer n) {
        return dataService.getCommentNumInNDayByUserId(uid, n);
    }

}
