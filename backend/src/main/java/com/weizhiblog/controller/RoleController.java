package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

/*
 * 该类是控制角色的控制器
 *
 * @createTime 07-17 19:46:15
 * @author Touka_
 * @classname com.weizhiblog.controller.CommentController
 * @lastModifiedTime 7月17日   19:46:15
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;

    /**
     * 判断该用户是否是管理员
     *
     * @param uid 用户id
     * @return 是或者否
     */
    @RequestMapping(value = "/isAdmin/{uid}", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean isAdmin(@PathVariable("uid") @NotNull Integer uid) {
        return roleService.isAdmin(uid);
    }

    /**
     * 将该用户设置成管理员
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    @PutMapping("/setAdmin/{uid}")
    public ResponseBean setAdmin(@PathVariable("uid") @NotNull Integer uid) {
        return roleService.setAdmin(uid);
    }

    /**
     * 将该用户设置成普通用户
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    @PutMapping("/setOrdinary/{uid}")
    public ResponseBean setOrdinary(@PathVariable("uid") @NotNull Integer uid) {
        return roleService.setOrdinary(uid);
    }


}
