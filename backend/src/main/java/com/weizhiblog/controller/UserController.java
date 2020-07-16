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
@RestController()
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/signup", method = {RequestMethod.POST})
    public ResponseBean userSignUp(@RequestBody User user){
        System.out.println(user);
        if (userService.signUpUser(user)==1) {
            return new ResponseBean(1,"注册用户成功！");
        }else {
            return new ResponseBean(0,"注册用户失败！请稍后重试");
        }
    }

    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public ResponseBean userLogin(@RequestBody User user){
        return null;
    }
}
