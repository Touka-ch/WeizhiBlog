package com.weizhiblog.mapper;

import com.weizhiblog.bean.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagsMapper {
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
    int insert(Tags record);

    int insertOrUpdate(Tags record);

    int insertOrUpdateSelective(Tags record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Tags record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Tags selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Tags record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Tags record);

    int updateBatch(List<Tags> list);

    int updateBatchSelective(List<Tags> list);

    int batchInsert(@Param("list") List<Tags> list);
}