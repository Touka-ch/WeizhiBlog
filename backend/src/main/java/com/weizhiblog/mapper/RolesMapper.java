package com.weizhiblog.mapper;

import com.weizhiblog.bean.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolesMapper {
    /**
     * 根据用户id删除用户的角色名
     *
     * @param id 用户id
     * @return 删除成功则返回 1
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入用户角色
     *
     * @param record 用户角色记录
     * @return 插入成功则返回 1
     */
    int insert(Roles record);

    /**
     * 插入用户角色，如果用户角色存在，则更新该文用户角色为 record
     *
     * @param record 用户角色记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Roles record);

    /**
     * 插入用户角色记录，如果用户角色存在，则更新该文用户角色为 record
     *
     * @param record 用户角色记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Roles record);

    /**
     * 插入或更新用户角色记录（不为null的字段），如果用户角色存在则改为更新
     *
     * @param record 用户角色记录
     * @return 插入成功则返回 1
     */
    int insertSelective(Roles record);

    /**
     * 根据用户id获取用户角色
     *
     * @param id 用户id
     * @return 存在此id对应的用户角色则返回该角色
     */
    Roles selectByPrimaryKey(Integer id);

    /**
     * 根据用户id更新用户角色名（不为null的字段）
     *
     * @param record 用户角色记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKeySelective(Roles record);

    /**
     * 根据用户id更新用户角色名
     *
     * @param record 用户角色记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKey(Roles record);

    /**
     * 批量更新用户角色名（根据每个用户id）
     *
     * @param list 用户角色名列表
     * @return 更新成功则返回 1
     */
    int updateBatch(List<Roles> list);

    /**
     * 批量更新用户角色名记录（根据每个用户id）
     *
     * @param list 用户角色名列表
     * @return 更新成功则返回 1
     */
    int updateBatchSelective(List<Roles> list);

    /**
     * 批量插入用户角色名
     *
     * @param list 用户角色列表
     * @return 更新成功则返回 1
     */
    int batchInsert(@Param("list") List<Roles> list);
}