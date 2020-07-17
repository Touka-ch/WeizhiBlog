package com.weizhiblog.mapper;

import com.weizhiblog.bean.Roles;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertOrUpdate(Roles record);

    int insertOrUpdateSelective(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);

    int updateBatch(List<Roles> list);

    int updateBatchSelective(List<Roles> list);

    int batchInsert(@Param("list") List<Roles> list);
}