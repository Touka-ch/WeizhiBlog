package com.weizhiblog.service;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.User;
import com.weizhiblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 注册用户
     * @param user 用户信息
     * @return 是否注册成功
     * 关于 status
     * 1：注册成功。
     * -1：注册失败。服务器错误！
     * 0：注册失败。未知原因！
     * -2：注册失败。此用户名已被占用！
     * -3：注册失败。此邮箱为已注册用户，请登录！
     * -4：注册失败。昵称重复。
     */
    public ResponseBean signUpUser(User user) {
        return new ResponseBean(1, "成功");
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @param captcha 验证码
     * @return 是否登录成功
     * 关于 status
     * 1：登录成功。可以跳转。
     * -1：登录失败。服务器错误！
     * 0：登录失败。未知原因！
     * -2：登录失败。验证码错误！
     * -3：登录失败。密码错误！
     * -4：登录失败。密码错误超过五次！
     */
    public ResponseBean loginUser(String username,
                                  String password,
                                  String captcha) {
        return new ResponseBean(1, "成功");
    }

    public ResponseBean deleteUser(Integer id) {
        return new ResponseBean(1, "成功");
    }


    public ResponseBean updateUser(Integer id,
                                   User user) {
        return new ResponseBean(1, "成功");
    }

    public ResponseBean listUsers() {
        return new ResponseBean(1, "成功");
    }

    public ResponseBean deleteSelectedUsers(List<Integer> ids) {
        return new ResponseBean(1, "成功");
    }

    public ResponseBean updateUserStatus(Integer id,
                                         Integer status) {
        return new ResponseBean(1, "成功");
    }

    public ResponseBean updateUserPassword(Integer id,
                                           String oldPwd,
                                           String newPwd,
                                           String captcha) {
        return new ResponseBean(1, "成功");
    }
}
