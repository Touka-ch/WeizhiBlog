package com.weizhiblog.service;

import com.weizhiblog.bean.*;
import com.weizhiblog.config.BeanConfig;
import com.weizhiblog.exception.MyRuntimeException;
import com.weizhiblog.mapper.*;
import com.weizhiblog.utils.AvatarUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    LoginService loginService;
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
        user.setPassword(BeanConfig.passwordEncoder().encode(user.getPassword()));
        //设置注册时间、默认启用用户、设置默认头像

        user.setRegTime(new Timestamp(System.currentTimeMillis()));
        if (user.getUserface() == null) {//如果设置了默认，就不管，否则使用默认头像
            user.setUserface(AvatarUtils.getRandAvatar());
        }
        user.setEnabled(true);
        int i = userMapper.insert(user);
        User user1 = userMapper.getUserByUsername(user.getUsername());
        if (i == 1) {
            int insert = rolesUserMapper.insert(RolesUser.builder().rid(2).uid(user1.getId()).build());
            if (insert == 1) {
                user1.setPassword(null);
                List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(user1.getId());
                List<String> roles = new ArrayList<>();
                for (RolesUser rolesUser : rolesUsers) {
                    roles.add("ROLE_" + rolesUser.getRid());
                }
                user1.setRoles(roles);
                user1.setPassword(null);
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
            List<Comment> comments = commentsMapper.listCommentsByAid(id1);
            if (comments.size() != 0) {//删除该用户每篇文章的评论,这里可能存在删除评论出错的问题。
                //具体表现为 先删父评论时，子评论有父评论的外键。这里先不管，遇到问题再说
                for (Comment comment : comments) {
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
            user.setPassword(null);
            return ResponseBean.builder().status(1).message("删除成功！").object(user).build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("未知错误！").object(user).build());
        }
    }


    public ResponseBean listUsers() {
        List<User> users = userMapper.listUsers();
        if (users == null) {
            return ResponseBean.builder().status(0).message("未知错误！").build();
        }
        for (User user : users) {
            user.setPassword(null);
            List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(user.getId());
            List<String> roles = new ArrayList<>();
            for (RolesUser rolesUser : rolesUsers) {
                roles.add("ROLE_" + rolesUser.getRid());
            }
            user.setRoles(roles);
        }
        return users.size() == 0 ?
                ResponseBean.builder().status(-2).message("用户数量为空！").build() :
                ResponseBean.builder().status(1).message("获取成功！").object(users).build();
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
        List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(user.getId());
        List<String> roles = new ArrayList<>();
        for (RolesUser rolesUser : rolesUsers) {
            roles.add("ROLE_" + rolesUser.getRid());
        }
        user.setRoles(roles);
        user.setPassword(null);
        return ResponseBean.builder().status(1).message("获取成功！").object(user).build();
    }

    public ResponseBean putUser(Integer id, User user) {
        if (id == null || user.getId() == null) {
            return ResponseBean.builder().status(-5).message("id不能为空！").build();
        }
        if (!id.equals(user.getId())) {
            return ResponseBean.builder().status(-7).message("id有误！").build();
        }
        User sqlUser = userMapper.selectByPrimaryKey(id);
        if (sqlUser == null) {
            return ResponseBean.builder().status(-6).message("此id无对应用户！").object(id).build();
        }
        if (user.getUsername() != null) {
            sqlUser = userMapper.getUserByUsername(user.getUsername());
            if (sqlUser != null && !sqlUser.getId().equals(id)) {
                return ResponseBean.builder().status(-2).message("用户名已被占用！").object(user.getUsername()).build();
            }
        }
        if (user.getEmail() != null) {
            sqlUser = userMapper.getUserByEmail(user.getEmail());
            if (sqlUser != null && !sqlUser.getId().equals(id)) {
                log.info(sqlUser.toString());
                log.info(id.toString());
                return ResponseBean.builder().status(-3).message("邮箱已被注册！").object(user.getEmail()).build();
            }
        }
        if (user.getNickname() != null) {
            sqlUser = userMapper.getUserByNickname(user.getNickname());
            if (sqlUser != null && !sqlUser.getId().equals(user.getId())) {
                return ResponseBean.builder().status(-4).message("昵称被占用！").object(user.getNickname()).build();
            }
        }
        sqlUser = userMapper.selectByPrimaryKey(id);
        user.setPassword(sqlUser.getPassword());
        user.setRoles(sqlUser.getRoles());
        user.setEnabled(user.getStatus());
        userMapper.updateByPrimaryKey(user);
        User user1 = userMapper.selectByPrimaryKey(id);
        user1.setPassword(null);
        List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(user1.getId());
        List<String> roles = new ArrayList<>();
        for (RolesUser rolesUser : rolesUsers) {
            roles.add("ROLE_" + rolesUser.getRid());
        }
        user1.setRoles(roles);
        user1.setPassword(null);
        return ResponseBean.builder().status(1).message("更新成功！").object(user1).build();
    }

    public ResponseBean patchUser(Integer id, User user) {
        log.info(user.toString());
        if (id == null||user.getId()==null) {
            return ResponseBean.builder().status(-5).message("id不能为空！").build();
        }
        if (user.getId()!=id){
            return ResponseBean.builder().status(-7).message("user中id必须和路径id相同！").object(id).build();
        }
        User sqlUser = userMapper.selectByPrimaryKey(id);
        if (sqlUser == null) {
            return ResponseBean.builder().status(-6).message("此id无对应用户！").object(id).build();
        }
        if (user.getUsername() != null) {
            sqlUser = userMapper.getUserByUsername(user.getUsername());
            if (sqlUser != null && !sqlUser.getId().equals(id)) {
                return ResponseBean.builder().status(-2).message("用户名已被占用！").object(user.getUsername()).build();
            }
        }
        if (user.getEmail() != null) {
            sqlUser = userMapper.getUserByEmail(user.getEmail());
            if (sqlUser != null && !sqlUser.getId().equals(id)) {
                return ResponseBean.builder().status(-3).message("邮箱已被注册！").object(user.getEmail()).build();
            }
        }
        if (user.getNickname() != null) {
            sqlUser = userMapper.getUserByNickname(user.getNickname());
            if (sqlUser != null && !sqlUser.getId().equals(user.getId())) {
                return ResponseBean.builder().status(-4).message("昵称被占用！").object(user.getNickname()).build();
            }
        }
        user.setEnabled(user.getStatus());
        sqlUser = userMapper.selectByPrimaryKey(id);
        user.setPassword(null);
        user.setRoles(sqlUser.getRoles());
        int i = userMapper.updateByPrimaryKeySelective(user);
        if (i == 1) {
            User user1 = userMapper.selectByPrimaryKey(user.getId());
            user1.setPassword(null);
            List<RolesUser> rolesUsers = rolesUserMapper.listRolesUserByUid(user1.getId());
            List<String> roles = new ArrayList<>();
            for (RolesUser rolesUser : rolesUsers) {
                roles.add("ROLE_" + rolesUser.getRid());
            }
            user1.setRoles(roles);
            user1.setPassword(null);
            return ResponseBean.builder().status(1).message("更新成功！").object(user1).build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("未知错误！").build());
        }
    }

}
