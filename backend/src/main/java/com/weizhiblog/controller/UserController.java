package com.weizhiblog.controller;

/*
 * 关于用户的一些控制器
 *
 * @createTime 07-15 12:5:1
 * @author Touka_
 * @classname com.weizhiblog.controller.UserController
 * @lastModifiedTime 7月15日   12:5:1
 */

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.User;
import com.weizhiblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * 用户注册
     *
     * @param user 用户的基本信息，可以不全填
     * @return 返回注册用户结果
     */
    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public ResponseBean userSignUp(@RequestBody User user) {
        return userService.signUpUser(user);
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @param captcha  验证码
     * @return 用户登录是否成功
     */
    @RequestMapping(value = "/login", method = {RequestMethod.POST})
    public ResponseBean userLogin(@RequestParam("username") String username,
                                  @RequestParam("password") String password,
                                  @RequestParam("captcha") String captcha) {
        return userService.loginUser(username, password, captcha);
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.DELETE})
    public ResponseBean userDelete(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    /**
     * 修改用户、更新用户信息
     *
     * @param user 用户新信息
     * @param id   用户id
     * @return 是都更新用户信息成功
     */
    @RequestMapping(value = "/{id}", method = {RequestMethod.POST})
    public ResponseBean userUpdate(@RequestBody User user,
                                   @PathVariable Integer id) {
        return userService.updateUser(id, user);
    }

    /**
     * 获取所有用户
     *
     * @return 所有用户，放在第三个参数obj里
     */
    @RequestMapping(value = "/all", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getAllUsers() {
        return userService.listUsers();
    }

    /**
     * 删除选定用户
     *
     * @param ids 要删除的用户的id组成的列表
     * @return 是否删除成功
     */
    @RequestMapping(value = "/selected", method = {RequestMethod.DELETE})
    public ResponseBean deleteSelectedUsers(@RequestBody List<Integer> ids) {
        return userService.deleteSelectedUsers(ids);
    }

    /**
     * 更新指定用户的状态为status
     *
     * @param id     用户id
     * @param status 用户更新后的状态
     * @return 是否更新成功
     */
    @RequestMapping(value = "/{id}/status", method = {RequestMethod.PUT, RequestMethod.POST})
    public ResponseBean updateUserStatus(@PathVariable Integer id,
                                         @RequestParam("status") Integer status) {
        return userService.updateUserStatus(id, status);
    }

    /**
     * 修改密码
     *
     * @param id      用户id
     * @param oldPwd  旧密码
     * @param newPwd  新密码
     * @param captcha 验证码
     * @return 是否更新密码成功
     */
    @RequestMapping(value = "/{id}/password", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean updateUserPassword(@PathVariable Integer id,
                                           @RequestParam("oldPwd") String oldPwd,
                                           @RequestParam("newPwd") String newPwd,
                                           @RequestParam("captcha") String captcha) {
        return userService.updateUserPassword(id, oldPwd, newPwd, captcha);
    }

}
