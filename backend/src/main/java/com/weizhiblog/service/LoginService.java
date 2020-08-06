package com.weizhiblog.service;

/*
 *
 * @createTime 08-03 13:42:56
 * @author Touka_
 * @classname com.weizhiblog.service.LoginService
 * @lastModifiedTime 8月3日   13:42:56
 */

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.RolesUser;
import com.weizhiblog.bean.User;
import com.weizhiblog.bean.dto.PasswordDto;
import com.weizhiblog.config.BeanConfig;
import com.weizhiblog.mapper.RolesMapper;
import com.weizhiblog.mapper.RolesUserMapper;
import com.weizhiblog.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LoginService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesUserMapper rolesUserMapper;
    @Autowired
    RolesMapper rolesMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.getUserByUsername(username);

        if (user == null) {
            return new User();
        }
        log.info(user.toString());
        log.info(BeanConfig.passwordEncoder().encode(user.getPassword()));
        List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(user.getId());
        List<String> roles = new ArrayList<>();
        for (RolesUser rolesUser : rolesUsers) {
            roles.add("ROLE_" + rolesUser.getRid());
        }
        user.setRoles(roles);
        log.info(user.toString());
        return user;
    }

    public ResponseBean updateUserPassword(PasswordDto passwordDto) {
        Integer id = passwordDto.getId();
        String oldPwd = passwordDto.getOldPwd();
        String newPwd = passwordDto.getNewPwd();

        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ResponseBean.builder().status(-4).message("不存在该用户！").build();
        }
        User userDetails = (User) loadUserByUsername(user.getUsername());
        BCryptPasswordEncoder encoder = (BCryptPasswordEncoder) BeanConfig.passwordEncoder();
        if (encoder.matches(oldPwd,userDetails.getPassword())){
            log.info(oldPwd);
            log.info(userDetails.getPassword());
            String encode = encoder.encode(newPwd);
            userMapper.updateByPrimaryKeySelective(User.builder().id(id).password(encode).build());
            log.info(encode);
            return ResponseBean.builder().status(1).message("修改成功").build();
        }

        return ResponseBean.builder().status(400).message("失败！").build();
    }
}
