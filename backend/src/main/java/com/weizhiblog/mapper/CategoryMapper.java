package com.weizhiblog.mapper;

import com.weizhiblog.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 根据文章id删除文章类别
     * @param id 文章id
     * @return 删除成功则返回 1
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入文章类别
     * @param record 文章类别记录
     * @return 插入成功则返回 1
     */
    int insert(Category record);

    /**
     * 插入或更新文章类别，如果文章类别存在，则更新该文章标签为 record
     * @param record 文章类别记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Category record);

    /**
     * 插入或更新文章类别记录（不为null的字段），如果文章id存在则改为更新
     * @param record 文章类别记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Category record);

    /**
     * 插入文章类别记录（不为null的字段），如果文章id存在则改为更新
     * @param record 文章类别记录
     * @return 插入或更新成功则返回 1
     */
    int insertSelective(Category record);

    /**
     * 根据文章id获取文章类别
     * @param id 文章id
     * @return 存在此id对应的文章则返回该文章类别
     */
    Category selectByPrimaryKey(Integer id);

    /**
     * 根据文章id更新文章类别记录（不为null的字段）
     * @param record 文章类别记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKeySelective(Category record);

    /**
     * 根据文章id更新文章类别（不为null的字段）
     * @param record 文章类别记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKey(Category record);

    /**
     * 批量更新文章类别（记录根据每个文章的id）
     * @param list 文章类别列表
     * @return 更新成功则返回 1
     */
    int updateBatch(List<Category> list);

    /**
     * 批量更新文章类别记录（记录根据每个文章的id）
     * @param list 文章类别列表
     * @return 更新成功则返回 1
     */
    int updateBatchSelective(List<Category> list);

    /**
     * 批量插入文章类别（根据每个文章的id）
     * @param list 文章类别列表
     * @return 插入成功则返回插入的行数
     */
    int batchInsert(@Param("list") List<Category> list);
}