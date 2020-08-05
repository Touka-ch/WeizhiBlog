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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseBean signUpUser(@RequestBody @Validated User user) {
        return userService.signUpUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteUser(@PathVariable @NotNull Integer id) {
        return userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseBean putUser(@PathVariable @NotNull Integer id,
                                @RequestBody @Validated User user) {
        return userService.putUser(id, user);
    }

    @PatchMapping("/{id}")
    public ResponseBean patchUser(@PathVariable @NotNull Integer id,
                                  @RequestBody User user) {
        return userService.patchUser(id, user);
    }


    @GetMapping
    public ResponseBean listUsers() {
        return userService.listUsers();
    }

    @GetMapping("{id}")
    private ResponseBean getUserById(@PathVariable("id") @NotNull Integer id) {
        return userService.getUserById(id);
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
    @RequestMapping(value = "/password", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean updateUserPassword(@RequestParam("id") Integer id,
                                           @RequestParam("oldPwd") @NotNull @Size(max = 20, min = 3) String oldPwd,
                                           @RequestParam("newPwd") @NotNull @Size(max = 20, min = 3) String newPwd,
                                           @RequestParam(value = "captcha", required = false) String captcha) {
        return userService.updateUserPassword(id, oldPwd, newPwd, captcha);
    }


}
