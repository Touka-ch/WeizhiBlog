package com.weizhiblog.bean;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Comments {
    /**
     *
     */
    private Integer id;

    /**
     * 文章ID
     */
    private Integer aid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 发布时间
     */
    private Timestamp publishTime;

    /**
     * -1表示正常回复，其他值表示是评论的回复
     */
    private Integer parentId;


    /**
     * 用户id
     */
    private Integer uid;
}

