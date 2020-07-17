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
public class Pv {
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