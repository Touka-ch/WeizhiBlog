package com.weizhiblog.service;
/*
 *
 * @createTime 07-17 19:46:24
 * @author Touka_
 * @classname com.weizhiblog.service.CommentService
 * @lastModifiedTime 7月17日   19:46:24
 */


import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.Roles;
import com.weizhiblog.mapper.RolesMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RoleService {
    @Autowired
    RolesMapper RoleMapper;

    /**
     * 判断该用户是否是管理员
     * @param id 用户id
     * @return 是或者否
     */
    public ResponseBean judgeRole_ad(Integer id ){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 将该用户设置成管理员
     * @param id 用户id
     * @param roles 角色信息
     * @return 是否更新成功
     */
    public ResponseBean updateRole_ad(Integer id, Roles roles){
        return ResponseBean.builder().status(1).message("").build();
    }
    /**
     * 将该用户设置成普通用户
     * @param id 用户id
     * @param roles 角色信息
     * @return  是否更新成功
     */
    public ResponseBean updateRole_com(Integer id,Roles roles){
        return ResponseBean.builder().status(1).message("").build();
    }
}
