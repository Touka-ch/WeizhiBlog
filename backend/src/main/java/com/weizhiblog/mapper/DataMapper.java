package com.weizhiblog.mapper;

import com.weizhiblog.bean.Data;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DataMapper {
    /**
     * delete by primary key
     *
     * @param id primaryKey
     * @return deleteCount
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert record to table
     *
     * @param record the record
     * @return insert count
     */
    int insert(Data record);

    int insertOrUpdate(Data record);

    int insertOrUpdateSelective(Data record);

    /**
     * insert record to table selective
     *
     * @param record the record
     * @return insert count
     */
    int insertSelective(Data record);

    /**
     * select by primary key
     *
     * @param id primary key
     * @return object by primary key
     */
    Data selectByPrimaryKey(Integer id);

    /**
     * update record selective
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKeySelective(Data record);

    /**
     * update record
     *
     * @param record the updated record
     * @return update count
     */
    int updateByPrimaryKey(Data record);

    int updateBatch(List<Data> list);

    int updateBatchSelective(List<Data> list);

    int batchInsert(@Param("list") List<Data> list);

    @Select("SELECT * from data WHERE uid = #{uid}")
    List<Data> listDatasByUid(Integer uid);

    @Delete("DELETE FROM data WHERE aid = #{aid} ")
    int deleteDataByAid(Integer aid);
}