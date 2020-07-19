package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pvview {
    /**
     * 一天的浏览量？
     */
    private BigDecimal pv;

    /**
     * 用户id
     */
    private Integer uid;
}