package com.weizhiblog.service;

import com.weizhiblog.bean.*;
import com.weizhiblog.exception.MyRuntimeException;
import com.weizhiblog.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RolesUserMapper rolesUserMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleTagsMapper articleTagsMapper;
    @Autowired
    DataMapper dataMapper;
    @Autowired
    CommentsMapper commentsMapper;

    /**
     * 注册用户
     *
     * @param user 用户信息
     * @return 是否注册成功
     */
    @Transactional
    @ExceptionHandler(MyRuntimeException.class)
    public ResponseBean signUpUser(User user) throws MyRuntimeException {
        //用户名已被占用 -2
        if (userMapper.getUserByUsername(user.getUsername()) != null) {
            return ResponseBean.builder().status(-2).message("此用户名已被占用！").object(user.getUsername()).build();
        }
        //邮箱已注册 -3
        if (userMapper.getUserByEmail(user.getEmail()) != null) {
            return ResponseBean.builder().status(-3).message("此邮箱为已注册用户，请登录！").object(user.getEmail()).build();
        }
        //昵称被占用 -4
        if (userMapper.getUserByNickname(user.getNickname()) != null) {
            throw new MyRuntimeException(
                    ResponseBean.builder().status(-4).message("昵称重复！").object(user.getNickname()).build()
            );
        }
        //设置注册时间、默认启用用户、设置默认头像
        user.setRegTime(new Timestamp(System.currentTimeMillis()));
        if (user.getEnabled() == null) {//如果设置了默认，就不管，否则默认启用
            user.setEnabled(true);
        }
        if (user.getUserface() == null) {//如果设置了默认，就不管，否则使用默认头像
            user.setUserface("/img/defaultUserface.png");
        }
        int i = userMapper.insert(user);
        User user1 = userMapper.getUserByUsername(user.getUsername());
        if (i == 1) {
            int insert = rolesUserMapper.insert(RolesUser.builder().rid(2).uid(user1.getId()).build());
            if (insert == 1) {
                return ResponseBean.builder().status(1).message("注册成功！").object(user1).build();
            } else {
                throw new MyRuntimeException(
                        ResponseBean.builder().status(0).message("服务器内部错误！").build()
                );
            }
        } else {
            throw new MyRuntimeException(
                    ResponseBean.builder().status(0).message("服务器内部错误！").build());
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
        User user = userMapper.getUserByUsername(username);
        if (user == null) {//用户不存在
            return ResponseBean.builder().status(-4).message("不存在该用户！").object(username).build();
        }
        List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(user.getId());
        List<Integer> roles = new ArrayList<>();
        for (RolesUser rolesUser : rolesUsers) {
            roles.add(rolesUser.getRid());
        }
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("roles", roles);
        return !user.getPassword().equals(password) ?
                ResponseBean.builder().status(-2).message("密码错误！").build() :
                ResponseBean.builder().status(1).message("登录成功！").object(map).build();
    }

    /**
     * 删除用户
     *
     * @param id 用户id
     * @return 是否删除成功
     */
    @Transactional
    public ResponseBean deleteUser(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);//尝试用主键获取 user
        if (user == null) {//如果 user 为空，说明不存在此 id 对应的 user
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(id).build();
        }
        List<Article> articles = articleMapper.listArticlesByUid(id);
        for (Article article : articles) {
            @NotNull Integer id1 = article.getId();
            dataMapper.deleteDataByAid(id1);
            List<Comments> comments = commentsMapper.listCommentsByAid(id1);
            if (comments.size() != 0) {//删除该用户每篇文章的评论,这里可能存在删除评论出错的问题。
                //具体表现为 先删父评论时，子评论有父评论的外键。这里先不管，遇到问题再说
                for (Comments comment : comments) {
                    commentsMapper.deleteByPrimaryKey(comment.getId());
                }
            }
            articleTagsMapper.deleteByAid(id1);
            //评论删完了就删文章
            articleMapper.deleteByPrimaryKey(id1);
        }
        //文章删完了删目录
        categoryMapper.deleteCategoryByUid(id);
        //别忘了删 该用户发表的评论
        commentsMapper.deleteCommentsByUid(id);
        List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(id);
        int i = rolesUserMapper.deleteRolesUsersByUid(id);//删角色
        if (i != rolesUsers.size()) {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("删除用户角色出错").build());
        }
        if (userMapper.deleteByPrimaryKey(id) == 1) {
            return ResponseBean.builder().status(1).message("删除成功！").object(user).build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("未知错误！").object(user).build());
        }
    }

    /**
     * 更新用户
     *
     * @param user 用户信息
     * @return 是否更新成功
     */
    @Transactional
    public ResponseBean updateUser(User user) {
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
        if (userMapper.updateByPrimaryKeySelective(user) == 1) {
            return ResponseBean.builder().status(1).message("更新成功！").object(userMapper.selectByPrimaryKey(user.getId())).build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("未知错误！").build());
        }
    }

    /**
     * 获取所有用户
     *
     * @return 所有用户
     */
    public ResponseBean listUsers() {
        List<User> users = userMapper.listUsers();
        if (users == null) {
            return ResponseBean.builder().status(0).message("未知错误！").build();
        }
        return users.size() == 0 ?
                ResponseBean.builder().status(-2).message("用户数量为空！").build() :
                ResponseBean.builder().status(1).message("获取成功！").object(users).build();
    }

    /**
     * 删除选中用户
     *
     * @param ids 选中用户列表
     * @return 是否删除成功
     */
    @Transactional
    public ResponseBean deleteSelectedUsers(List<Integer> ids) {
        for (Integer id : ids) {
            if (userMapper.selectByPrimaryKey(id) == null) {
                throw new MyRuntimeException(ResponseBean.builder().status(-2).message("该用户不存在").object(id).build());
            }
            try {
                this.deleteUser(id);
            } catch (MyRuntimeException e) {
                throw e;
            }
        }
        return ResponseBean.builder().status(1).message("删除成功！").build();
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
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ResponseBean.builder().status(-2).message("此id不存在对应用户！").build();
        }
        if (user.getEnabled() == enable) {
            return ResponseBean.builder().status(-3).message("状态未改变！").build();
        }
        user.setEnabled(enable);
        if (userMapper.updateByPrimaryKey(user) == 1) {
            return ResponseBean.builder().status(1).message("更新成功！").build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("数据库错误！询问管理员！").build());
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
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("未知原因！").build());
        }
    }

    /**
     * 根据id获取用户
     *
     * @param id 用户id
     * @return Res
     */
    public ResponseBean getUserById(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null) {
            return ResponseBean.builder().status(-2).message("用户不存在！").build();
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(user).build();
    }
}
