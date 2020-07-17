package com.weizhiblog.mapper;

import com.weizhiblog.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertOrUpdate(User record);

    int insertOrUpdateSelective(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int updateBatch(List<User> list);

    int updateBatchSelective(List<User> list);

    int batchInsert(@Param("list") List<User> list);

    /* ************************************************************
     以下为自己写的
     *************************************************************/

    /**
     * 通过username获取user
     *
     * @param username 用户名
     * @return 如果存在此username 则返回该user
     */
    User getUserByUsername(String username);

    /**
     * 通过email查找user
     *
     * @param email 用户邮箱
     * @return 如果存在此Email 返回该user
     */
    User getUserByEmail(String email);

    /**
     * 根据昵称找user
     *
     * @param nickname 昵称
     * @return 如果存在则返回该user
     */
    User getUserByNickname(String nickname);
}