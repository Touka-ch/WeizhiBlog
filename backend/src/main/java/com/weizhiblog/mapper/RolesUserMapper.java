package com.weizhiblog.mapper;

import com.weizhiblog.bean.RolesUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolesUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RolesUser record);

    int insertOrUpdate(RolesUser record);

    int insertOrUpdateSelective(RolesUser record);

    int insertSelective(RolesUser record);

    RolesUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RolesUser record);

    int updateByPrimaryKey(RolesUser record);

    int updateBatch(List<RolesUser> list);

    int updateBatchSelective(List<RolesUser> list);

    int batchInsert(@Param("list") List<RolesUser> list);
}