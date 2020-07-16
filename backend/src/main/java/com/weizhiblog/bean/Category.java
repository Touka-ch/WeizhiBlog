package com.weizhiblog.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Category {
    /**
     *
     */
    private Integer id;

    /**
     * 目录名
     */
    private String cateName;

    /**
     * 目录创建日期
     */
    private Date date;
}

