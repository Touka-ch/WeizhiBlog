package com.weizhiblog.mapper;

import com.weizhiblog.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper {

    /**
     * 根据id删除用户
     *
     * @param id id
     * @return 删除成功则返回 1
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入用户
     *
     * @param record 用户记录
     * @return 插入成功则返回 1
     */
    int insert(User record);

    /**
     * 插入用户，如果用户存在，则更新该用户为 record
     *
     * @param record 用户记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(User record);

    /**
     * 插入用户记录，如果用户存在，则更新该标签为 record
     *
     * @param record 用户记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(User record);

    /**
     * 插入用户记录（不为null的字段），如果id存在则改为更新
     *
     * @param record 用户记录
     * @return 插入或更新成功则返回 1
     */
    int insertSelective(User record);

    /**
     * 根据id获取用户
     *
     * @param id id
     * @return 存在此id对应的用户则返回该用户
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 根据id更新用户记录（不为null的字段）
     *
     * @param record 用户记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * 根据id更新用户
     *
     * @param record 用户记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKey(User record);

    /**
     * 批量更新用户（根据每个id）
     *
     * @param list 用户列表
     * @return 更新成功则返回 1
     */
    int updateBatch(List<User> list);

    /**
     * 批量更新用户记录（根据每个id）
     *
     * @param list 用户记录
     * @return 更新成功则返回 1
     */
    int updateBatchSelective(List<User> list);

    /**
     * 批量插入用户根据每个id）
     *
     * @param list 用户列表
     * @return 插入成功则返回 1
     */
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

    /**
     * 列出用户列表
     *
     * @return 如果存在用户列表则返回List<User>
     */
    List<User> listUsers();


}