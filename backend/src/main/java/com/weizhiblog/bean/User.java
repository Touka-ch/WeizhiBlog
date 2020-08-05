package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    /**
     * 用户名
     */
    @Size(max = 20, min = 3)
    @NotNull(message = "用户名不能为空！")
    private String username;

    /**
     * 用户昵称
     */
    @Size(max = 20, min = 3)
    @NotNull(message = "昵称不能为空！")
    private String nickname;

    /**
     * 用户密码
     */
    @Size(max = 20, min = 3)
    @NotNull(message = "密码不能为空！")
    private String password;

    /**
     * 用户状态
     */
    private Boolean enabled;

    /**
     * 用户邮箱
     */
    @Email
    @NotNull
    private String email;

    /**
     * 用户头像地址
     */
    private String userface;

    /**
     * 注册时间
     */
    private Date regTime;
}