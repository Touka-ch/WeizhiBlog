package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class Data {
    private Integer id;

    private Integer uid;

    private Integer aid;

    private Timestamp day;

    private Integer pv;

    private Integer commentNum;

    private Integer likeNum;
}