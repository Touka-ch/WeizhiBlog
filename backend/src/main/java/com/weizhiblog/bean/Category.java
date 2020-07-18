package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private Integer id;

    /**
     * 目录名
     */
    private String cateName;
    /**
     * 创建日期
     */
    private Date date;
}