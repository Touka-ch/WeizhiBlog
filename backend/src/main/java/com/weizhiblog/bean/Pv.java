package com.weizhiblog.bean;

import lombok.Data;

import java.util.Date;

@Data
public class Pv {
    /**
     *
     */
    private Integer id;

    /**
     * 日期
     */
    private Date countDate;

    /**
     * 浏览量
     */
    private Integer pv;

    /**
     * 用户id
     */
    private Integer uid;
}

