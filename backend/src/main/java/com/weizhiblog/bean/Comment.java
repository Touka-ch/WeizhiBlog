package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 发表时间
     */
    private Timestamp publishTime;

    /**
     * -1表示正常回复，其他值表示是评论的回复
     */
    private Integer parentId;

    /**
     * 发表人id
     */
    private Integer uid;
}