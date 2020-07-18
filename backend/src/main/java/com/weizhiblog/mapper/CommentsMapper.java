package com.weizhiblog.mapper;

import com.weizhiblog.bean.Comments;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentsMapper {
    /**
     * delete by primary key
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     * @param record the record
     * @return insert count
     */
    int insert(Comments record);

    int insertOrUpdate(Comments record);

    int insertOrUpdateSelective(Comments record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Comments record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Comments selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Comments record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Comments record);

    int updateBatch(List<Comments> list);

    int updateBatchSelective(List<Comments> list);

    int batchInsert(@Param("list") List<Comments> list);
}