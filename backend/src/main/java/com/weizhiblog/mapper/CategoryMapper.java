package com.weizhiblog.mapper;

import com.weizhiblog.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
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
    int insert(Category record);

    int insertOrUpdate(Category record);

    int insertOrUpdateSelective(Category record);

    /**
     * insert record to table selective
     * @param record the record
     * @return insert count
     */
    int insertSelective(Category record);

    /**
     * select by primary key
     * @param id primary key
     * @return object by primary key
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * update record selective
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * update record
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Category record);

    int updateBatch(List<Category> list);

    int updateBatchSelective(List<Category> list);

    int batchInsert(@Param("list") List<Category> list);
}