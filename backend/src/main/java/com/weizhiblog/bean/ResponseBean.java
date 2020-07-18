package com.weizhiblog.bean;

/*
 *
 * @createTime 07-15 11:41:48
 * @author Touka_
 * @classname com.weizhiblog.bean.ResBean
 * @lastModifiedTime 7月15日   11:22:38
 */

import lombok.Builder;
import lombok.Getter;

/**
 * 用于返回给前端信息，是成功还是失败，失败原因是什么？
 */
@Builder
@Getter
public class ResponseBean {
    //1代表成功 0代表失败 其它待定义
    private Integer status;
    //失败原因
    private String message;
    //封装对象
    private Object object;
}
