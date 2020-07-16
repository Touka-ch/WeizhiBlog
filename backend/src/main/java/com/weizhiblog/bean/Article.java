package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private Integer id;
    /**
     * 标题
     */
    private String title;

    /**
    * md文件源码
    */
    private String mdContent;

    /**
    * html源码
    */
    private String htmlContent;

    /**
     * 概述
     */
    private String summary;

    /**
     * 目录id
     */
    private Integer cid;

    /**
     * 用户id
     */
    private Integer uid;

    /**
     * 发布时间
     */
    private Timestamp publishDate;

    /**
     * 最后编辑时间
     */
    private Timestamp editTime;

    /**
     * 评论数
     */
    private Integer commentNum;

    /**
     * 点赞数
     */
    private Integer likeNum;

    /**
    * 0表示草稿箱，1表示已发表，2表示已删除
    */
    private Integer state;

    /**
     * 浏览量
     */
    private Integer pageView;
}