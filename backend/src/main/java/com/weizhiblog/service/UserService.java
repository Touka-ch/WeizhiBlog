package com.weizhiblog.service;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.User;
import com.weizhiblog.exception.MyRuntimeException;
import com.weizhiblog.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Log4j2
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    public ResponseBean signUpUser(User user) {
        try {
            //用户名已被占用 -2
            if (userMapper.getUserByUsername(user.getUsername()) != null) {
                return ResponseBean.builder().status(-2).message("此用户名已被占用！").build();
            }
            //邮箱已注册 -3
            if (userMapper.getUserByEmail(user.getEmail()) != null) {
                return ResponseBean.builder().status(-3).message("此邮箱为已注册用户，请登录！").build();
            }
            //昵称被占用 -4
            if (userMapper.getUserByNickname(user.getNickname()) != null) {
                return ResponseBean.builder().status(-4).message("昵称重复！").build();
            }
            //设置注册时间、默认启用用户、设置默认头像
            user.setRegTime(new Date());
            if (user.getEnabled() == null) {//如果设置了默认，就不管，否则默认启用
                user.setEnabled(true);
            }
            if (user.getUserface() == null) {//如果设置了默认，就不管，否则使用默认头像
                user.setUserface("/img/defaultUserface.png");
            }
            //三元表达式返回，简化代码
            return userMapper.insert(user) == 1 ?//执行插入
                    ResponseBean.builder().status(1).message("注册成功！").build() :
                    ResponseBean.builder().status(0).message("未知原因！").build();
        } catch (Exception e) {//都到这地步了，实在不知道还有啥没考虑到
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 用户登录
     *
     * @param username 用户名
     * @param password 用户密码
     * @param captcha  验证码
     * @return 是否登录成功
     */
    public ResponseBean loginUser(String username,
                                  String password,
                                  String captcha) {
        try {
            User user = userMapper.getUserByUsername(username);
            if (user == null) {//用户不存在
                return ResponseBean.builder().status(-4).message("此id无对应用户！").build();
            }
            return !user.getPassword().equals(password) ?
                    ResponseBean.builder().status(-2).message("密码错误！").build() :
                    ResponseBean.builder().status(1).message("登录成功！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    public ResponseBean deleteUser(Integer id) {
        try {
            User user = userMapper.selectByPrimaryKey(id);//尝试用主键获取 user
            if (user == null) {//如果 user 为空，说明不存在此 id 对应的 user
                return ResponseBean.builder().status(-2).message("id不存在！").build();
            }
            return userMapper.deleteByPrimaryKey(id) == 1 ?//受影响行数为 1 说明删除成功
                    ResponseBean.builder().status(1).message("删除成功！").build() :
                    ResponseBean.builder().status(0).message("未知错误！").build();
        } catch (Exception e) {
            log.error(e);
            return ResponseBean.builder().status(-1).message("服务器错误").build();
        }
    }

    /**
     * 更新用户
     *
     * @param user 用户信息
     * @return 是否更新成功
     */
    public ResponseBean updateUser(User user) {
        try {
            if (user.getId() == null) {
                return ResponseBean.builder().status(-5).message("id不能为空！").build();
            }
            User sqlUser = userMapper.selectByPrimaryKey(user.getId());
            if (sqlUser == null) {
                return ResponseBean.builder().status(-6).message("此id无对应用户！").object(user.getId()).build();
            }
            if (user.getUsername() != null) {
                sqlUser = userMapper.getUserByUsername(user.getUsername());
                if (sqlUser != null && !sqlUser.getId().equals(user.getId())) {
                    return ResponseBean.builder().status(-2).message("用户名已被占用！").object(user.getUsername()).build();
                }
            }
            if (user.getEmail() != null) {
                sqlUser = userMapper.getUserByEmail(user.getEmail());
                if (sqlUser != null && !sqlUser.getId().equals(user.getId())) {
                    return ResponseBean.builder().status(-3).message("邮箱已被注册！").object(user.getEmail()).build();
                }
            }
            if (user.getNickname() != null) {
                sqlUser = userMapper.getUserByNickname(user.getNickname());
                if (sqlUser != null && !sqlUser.getId().equals(user.getId())) {
                    return ResponseBean.builder().status(-4).message("昵称被占用！").object(user.getNickname()).build();
                }
            }
            return userMapper.updateByPrimaryKey(user) == 1 ?
                    ResponseBean.builder().status(1).message("更新成功！").object(user).build() :
                    ResponseBean.builder().status(0).message("未知错误！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 获取所有用户
     *
     * @return 所有用户
     */
    public ResponseBean listUsers() {
        try {
            List<User> users = userMapper.listUsers();
            if (users == null) {
                return ResponseBean.builder().status(0).message("未知错误！").build();
            }
            return users.size() == 0 ?
                    ResponseBean.builder().status(-2).message("用户数量为空！").build() :
                    ResponseBean.builder().status(1).message("获取成功！").object(users).build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 删除选中用户
     *
     * @param ids 选中用户列表
     * @return 是否删除成功
     */
    @Transactional
    public ResponseBean deleteSelectedUsers(List<Integer> ids) {
        try {
            for (Integer id : ids) {
                if (userMapper.selectByPrimaryKey(id) == null) {
                    throw new MyRuntimeException(2, "删除用户不存在！");
                }
                if (userMapper.deleteByPrimaryKey(id) != 1) {
                    throw new MyRuntimeException(0, "未知错误！");
                }
            }
            return ResponseBean.builder().status(1).message("删除成功！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 更新用户状态
     *
     * @param id     用户id
     * @param enable 新状态
     * @return 是否更新成功
     */
    public ResponseBean updateUserStatus(Integer id,
                                         boolean enable) {
        try {
            User user = userMapper.selectByPrimaryKey(id);
            if (user == null) {
                return ResponseBean.builder().status(-2).message("此id不存在对应用户！").build();
            }
            if (user.getEnabled() == enable) {
                return ResponseBean.builder().status(-2).message("状态未改变！").build();
            }
            user.setEnabled(enable);
            return userMapper.updateByPrimaryKey(user) == 1 ?
                    ResponseBean.builder().status(1).message("更新成功！").build() :
                    ResponseBean.builder().status(0).message("未知错误！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 修改用户密码
     *
     * @param id      用户id
     * @param oldPwd  原密码
     * @param newPwd  新密码
     * @param captcha 验证码
     * @return 是否修改成功
     */
    public ResponseBean updateUserPassword(Integer id,
                                           String oldPwd,
                                           String newPwd,
                                           String captcha) {
        try {
            User user = userMapper.selectByPrimaryKey(id);
            if (user == null) {
                return ResponseBean.builder().status(-4).message("不存在该用户！").build();
            }
            if (!user.getPassword().equals(oldPwd)) {
                return ResponseBean.builder().status(-2).message("原密码错误！").build();
            }
            if (user.getPassword().equals(newPwd)) {
                return ResponseBean.builder().status(-3).message("新旧密码相同！").build();
            }
            user.setPassword(newPwd);
            if (userMapper.updateByPrimaryKey(user) == 1) {
                return ResponseBean.builder().status(1).message("修改成功！").build();
            } else {
                return ResponseBean.builder().status(0).message("未知原因！").build();
            }
        } catch (Exception e) {
            log.error(e);
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }
}
