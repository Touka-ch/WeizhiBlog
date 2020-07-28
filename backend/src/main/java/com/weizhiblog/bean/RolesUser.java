package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolesUser {
    private Integer id;

    /**
     * 角色id
     */
    private Integer rid;

    /**
     * 用户id
     */
    private Integer uid;
}