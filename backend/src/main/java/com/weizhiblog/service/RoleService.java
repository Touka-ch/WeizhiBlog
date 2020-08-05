package com.weizhiblog.service;
/*
 *
 * @createTime 07-17 19:46:24
 * @author Touka_
 * @classname com.weizhiblog.service.CommentService
 * @lastModifiedTime 7月17日   19:46:24
 */


import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.RolesUser;
import com.weizhiblog.bean.User;
import com.weizhiblog.exception.MyRuntimeException;
import com.weizhiblog.mapper.RolesMapper;
import com.weizhiblog.mapper.RolesUserMapper;
import com.weizhiblog.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class RoleService {
    @Autowired
    RolesMapper roleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesUserMapper rolesUserMapper;

    /**
     * 判断该用户是否是管理员
     *
     * @param uid 用户id
     * @return 是或者否
     */
    public ResponseBean isAdmin(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return ResponseBean.builder().status(-2).message("用户不存在").object(uid).build();
        }
        List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(uid);
        if (rolesUsers == null || rolesUsers.size() == 0) {
            rolesUserMapper.insert(RolesUser.builder().uid(uid).rid(2).build());
            return ResponseBean.builder().status(1).message("获取成功").object(false).build();
        }
        for (RolesUser rolesUser : rolesUsers) {
            if (rolesUser.getRid() == 1) {
                return ResponseBean.builder().status(1).message("获取成功").object(true).build();
            }
        }
        return ResponseBean.builder().status(1).message("获取成功").object(false).build();
    }

    /**
     * 将该用户设置成管理员
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    @Transactional
    public ResponseBean setAdmin(Integer uid) {
        ResponseBean responseBean = isAdmin(uid);
        if (responseBean.getStatus() != 1) {
            return responseBean;
        }
        if ((boolean) responseBean.getObject()) {
            return ResponseBean.builder().status(-3).message("此用户已经是管理员").build();
        }
        int i = rolesUserMapper.insert(RolesUser.builder().uid(uid).rid(1).build());
        if (i == 1) {
            return ResponseBean.builder().status(1).message("设置成功").build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("数据库错误").build());
        }
    }

    /**
     * 将该用户设置成普通用户
     *
     * @param uid 用户id
     * @return 是否更新成功
     */
    @Transactional
    public ResponseBean setOrdinary(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return ResponseBean.builder().status(-2).message("用户不存在").object(uid).build();
        }
        List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(uid);
        if (rolesUsers == null || rolesUsers.size() == 0) {
            rolesUserMapper.insert(RolesUser.builder().uid(uid).rid(2).build());
            return ResponseBean.builder().status(-3).message("用户已经是普通用户").object(uid).build();
        }
        if (rolesUsers.size() == 1 && rolesUsers.get(0).getRid() == 2) {
            return ResponseBean.builder().status(-3).message("用户已经是普通用户").object(uid).build();
        }
        rolesUserMapper.deleteRolesUsersByUid(uid);
        rolesUserMapper.insert(RolesUser.builder().uid(uid).rid(2).build());
        return ResponseBean.builder().status(1).message("设置成功").build();
    }
}
