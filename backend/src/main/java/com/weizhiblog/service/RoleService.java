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
import com.weizhiblog.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RoleService {
    @Autowired
    RolesMapper roleMapper;
    UserMapper userMapper;

    /**
     * 判断该用户是否是管理员
     * @param id 用户id
     * @return 是或者否
     */
    public ResponseBean judgeRole_ad(Integer id ){

        try{
            Roles roles_1=roleMapper.selectByPrimaryKey(id);
            String roleName_1=roles_1.getName();
            if(userMapper.selectByPrimaryKey(id)==null){
                return ResponseBean.builder().status(-2).message("该用户不存在！").build();
            }
            if(roleName_1.equals("Administrator")){
                return ResponseBean.builder().status(1).message("该用户是管理员！").build();
            }
            return ResponseBean.builder().status(1).message("该用户不是管理员！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 将该用户设置成管理员
     * @param id 用户id
     * @param roles 角色信息
     * @return 是否更新成功
     */
    public ResponseBean updateRole_ad(Integer id, Roles roles){
        try{
            Roles roles_2=roleMapper.selectByPrimaryKey(id);
            String roleName_2=roles_2.getName();
            if(userMapper.selectByPrimaryKey(id)==null){
                return ResponseBean.builder().status(-2).message("该用户不存在！").build();
            }
            if(roleName_2.equals("Administrator")){
                return ResponseBean.builder().status(-3).message("该用户已经是管理员！").build();
            }
            return roleMapper.updateByPrimaryKey(roles)==1?
                    ResponseBean.builder().status(1).message("该用户已设置为管理员！").build():
                    ResponseBean.builder().status(0).message("未知原因！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }
    /**
     * 将该用户设置成普通用户
     * @param id 用户id
     * @param roles 角色信息
     * @return  是否更新成功
     */
    public ResponseBean updateRole_com(Integer id,Roles roles){
        try{
            Roles roles_3=roleMapper.selectByPrimaryKey(id);
            String roleName_3=roles_3.getName();
            if(userMapper.selectByPrimaryKey(id)==null){
                return ResponseBean.builder().status(-2).message("该用户不存在！").build();
            }
            if(roleName_3.equals("Ordinary user")){
                return ResponseBean.builder().status(-3).message("该用户已经是普通用户！").build();
            }
            return roleMapper.updateByPrimaryKey(roles)==1?
                    ResponseBean.builder().status(1).message("该用户已设置为普通用户！").build():
                    ResponseBean.builder().status(0).message("未知原因！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }
}
