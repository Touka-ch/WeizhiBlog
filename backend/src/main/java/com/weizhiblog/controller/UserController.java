package com.weizhiblog.controller;

/*
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于控制 user 的创建更改删除和更新状态
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
//注册
    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public ResponseBean userSignUp(@RequestBody User user){
        System.out.println(user);
        if (userService.signUpUser(user)==1) {
            return new ResponseBean(1,"注册用户成功！");
        }else {
            return new ResponseBean(0,"注册用户失败！请稍后重试");
        }
    }
//登录
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public ResponseBean userLogin(@RequestBody User user){
        System.out.println(user);
        if(userService.loginUser(user)==1){
            return new ResponseBean(1,"用户登录成功！");
        }else {
            return new ResponseBean(0,"用户登录失败！请稍后重试");
        }
    }
//销毁
    @RequestMapping(value="/{id}",method={RequestMethod.DELETE})
    public ResponseBean userDelete(@RequestBody User user){
        System.out.println(user);
        if(userService.deleteUser(user)==1){
            return new ResponseBean(1,"删除用户成功！");
        }else {
            return new ResponseBean(0,"删除用户失败！请稍后重试");
        }
    }
//修改
    @RequestMapping(value="/{id}",method={RequestMethod.POST})
    public ResponseBean userChange(@RequestBody User user){
        System.out.println(user);
        if(userService.changeUser(user)==1){
            return new ResponseBean(1,"修改用户成功！");
        }else {
            return new ResponseBean(0,"修改用户失败！请稍后重试");
        }
    }
//获取所有用户
    @RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
    public ResponseBean userGet_all(@RequestBody User user){
        System.out.println(user);
        if(userService.get_allUser(user)==1){
            return new ResponseBean(1,"获取所有用户成功！");
        }else {
            return new ResponseBean(0,"获取所有用户失败！请稍后重试");
        }
    }
//批量消除用户
    @RequestMapping(method={RequestMethod.DELETE})
    public ResponseBean userDel_all(@RequestBody User user){
        System.out.println(user);
        if(userService.del_allUser(user)==1){
            return new ResponseBean(1,"批量删除用户成功！");
        }else {
            return new ResponseBean(0,"批量删除用户失败！请稍后重试");
        }
    }
//修改状态
    @RequestMapping(value="/{id}/status",method={RequestMethod.PUT,RequestMethod.POST})
    public ResponseBean userChange_status(@RequestBody User user){
        System.out.println(user);
        if(userService.change_statusUser(user)==1){
            return new ResponseBean(1,"修改状态成功！");
        }else {
            return new ResponseBean(0,"修改状态失败！请稍后重试");
        }
    }
//修改密码
    @RequestMapping(value="/{id}/pwd",method={RequestMethod.POST,RequestMethod.PUT})
    public ResponseBean userChange_pwd(@RequestBody User user){
        System.out.println(user);
        if(userService.change_pwdUser(user)==1){
            return new ResponseBean(1,"修改密码成功！");
        }else {
            return new ResponseBean(0,"修改密码失败！请稍后重试");
        }
    }

}
