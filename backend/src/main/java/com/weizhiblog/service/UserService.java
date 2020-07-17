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

    /**
     * 删除用户
     * @param id 用户id
     * @return 是否删除成功
     * 关于 status
     * 1：删除成功！
     * -1：删除失败，服务器错误！
     * 0：删除失败，未知原因！
     * */
    public ResponseBean deleteUser(Integer id) {
        return new ResponseBean(1, "成功");
    }

    /**
     * 更新用户
     * @param id 用户id
     * @param user 用户信息
     * @return 是否更新成功
     * 关于 status
     * 1：更新成功！
     * -1：更新失败，服务器错误！
     * 0：更新失败，未知原因！
     */
    public ResponseBean updateUser(Integer id,
                                   User user) {
        return new ResponseBean(1, "成功");
    }

    /**
     * 显示所有用户
     * @return 是否成功显示所有用户
     * 关于 status
     * 1：显示成功！
     * -1：显示失败，服务器错误！
     * 0：显示失败未知原因！
     */
    public ResponseBean listUsers() {
        return new ResponseBean(1, "成功");
    }

    /**
     * 删除选中用户
     * @param ids 选中用户列表
     * @return 是否删除成功
     * 关于 status
     * 1：删除成功！
     * -1：删除失败，服务器错误！
     * 0：删除失败，未知原因！
     * 2：删除失败，删除了不存在的用户！
     */
    public ResponseBean deleteSelectedUsers(List<Integer> ids) {
        return new ResponseBean(1, "成功");
    }

    /**
     * 更新用户状态
     * @param id 用户id
     * @param status 新状态
     * @return 是否更新成功
     * 关于 status
     * 1：更新成功！
     * -1：更新失败，服务器错误！
     * 0：更新失败，未知原因！
     * 2：更新失败，用户不存在！
     */
    public ResponseBean updateUserStatus(Integer id,
                                         Integer status) {
        return new ResponseBean(1, "成功");
    }

    /**
     * 修改用户密码
     * @param id 用户id
     * @param oldPwd 原密码
     * @param newPwd 新密码
     * @param captcha 验证码
     * @return 是否修改成功
     * 关于 status
     * 1：修改成功！
     * -1：修改失败，服务器错误！
     * 0：修改失败，未知原因！
     * 2：修改失败，原密码错误！
     * 3：修改失败，新密码不符合要求或与原密码一样！
     * 4：修改失败，验证码错误！
     */
    public ResponseBean updateUserPassword(Integer id,
                                           String oldPwd,
                                           String newPwd,
                                           String captcha) {
        return new ResponseBean(1, "成功");
    }
}
