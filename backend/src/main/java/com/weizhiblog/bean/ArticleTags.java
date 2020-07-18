package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleTags {
    private Integer id;

    /**
     * 文章id
     */
    private Integer aid;

    /**
     * 标签id
     */
    private Integer tid;
}