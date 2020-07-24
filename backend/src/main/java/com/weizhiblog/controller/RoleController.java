package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.Roles;
import com.weizhiblog.service.RoleService;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
@RestController
@RequestMapping("/comments")
public class RoleController {
    @Autowired
    RoleService roleService;
    /**
     * 判断该用户是否是管理员
     * @param id 用户id
     * @return 是或者否
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean Role_adJudge(@RequestParam("id") @NotNull Integer id){
        return roleService.judgeRole_ad(id);
    }
    /**
     * 将该用户设置成管理员
     * @param id 用户id
     * @param roles 角色信息
     * @return 是否更新成功
     */
    @RequestMapping(value = "/update/ad", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean Role_adUpdate(@RequestParam("id") @NotNull Integer id,
                                      @RequestBody Roles roles){
        return roleService.updateRole_ad(id,roles);
    }
    /**
     * 将该用户设置成普通用户
     * @param id 用户id
     * @param roles 角色信息
     * @return  是否更新成功
     */
    @RequestMapping(value = "/update/com", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean Role_comUpdate(@RequestParam("id") @NotNull Integer id,
                                       @RequestBody Roles roles){
        return roleService.updateRole_com(id,roles);
    }


}
