package com.weizhiblog.service;

import com.weizhiblog.bean.User;
import com.weizhiblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public Integer signUpUser(User user){
        return 1;
    }
    public Integer loginUser(User user){
        return 1;
    }
    public List<User> getAllUsers(){
        System.out.println(userMapper.listUsers());
        return null;
    }
    public Integer del_allUser(User user){
        return 1;
    }
    public Integer deleteUser(User user) {return 1; }

    public Integer changeUser(User user) {return 1; }

    public Integer get_allUser(User user) {return 1; }



    public Integer change_statusUser(User user) {return 1; }

    public Integer change_pwdUser(User user) {return 1; }
}
