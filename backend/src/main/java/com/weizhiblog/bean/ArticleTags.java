package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleTags {
    /**
     *
     */
    private Integer id;

    /**
     * 文章ID
     */
    private Integer aid;

    /**
     * 标签id
     */
    private Integer tid;
}

