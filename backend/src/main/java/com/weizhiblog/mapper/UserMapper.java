package com.weizhiblog.mapper;

import com.weizhiblog.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> listUsers();
}
