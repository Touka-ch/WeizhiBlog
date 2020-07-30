package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.RoleService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/*
 * 该类是控制角色的控制器
 *
 * @createTime 07-17 19:46:15
 * @author Touka_
 * @classname com.weizhiblog.controller.CommentController
 * @lastModifiedTime 7月17日   19:46:15
 */
@Log4j2
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
    @RequestMapping(value = "/isAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean isAdmin(@RequestParam("uid") @NotNull Integer uid) {
        return roleService.isAdmin(uid);
    }

    /**
     * 将该用户设置成管理员
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    @RequestMapping(value = "/setAdmin", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean setAdmin(@RequestParam("uid") @NotNull Integer uid) {
        return roleService.setAdmin(uid);
    }

    /**
     * 将该用户设置成普通用户
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    @RequestMapping(value = "/setOrdinary", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean setOrdinary(@RequestParam("uid") @NotNull Integer uid) {
        return roleService.setOrdinary(uid);
    }


}
