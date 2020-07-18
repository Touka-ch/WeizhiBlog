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
public class Totalpvview {
    /**
     * 总浏览
     */
    private BigDecimal totalPv;

    /**
     * 用户id
     */
    private Integer uid;
}