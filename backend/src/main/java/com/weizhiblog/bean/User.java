package com.weizhiblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

/*
 *
 * @createTime 07-15 12:33:03
 * @author Touka_
 * @classname com.weizhiblog.bean.User
 * @lastModifiedTime 7月15日   12:22:48
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;
    private String username;
    private String password;
    private String userface;
    private String nickname;
    private Integer enabled;
    private List<Role> roles;
    private String email;
    private Timestamp regTime;
}
