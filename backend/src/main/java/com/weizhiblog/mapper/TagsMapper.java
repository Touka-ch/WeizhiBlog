package com.weizhiblog.mapper;

import com.weizhiblog.bean.Tags;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TagsMapper {
    /**
     * 根据id删除标签
     *
     * @param id id
     * @return 删除成功则返回 1
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入标签
     *
     * @param record 标签记录
     * @return 插入成功则返回 1
     */
    int insert(Tags record);

    /**
     * 插入标签，如果标签存在，则更新该标签为 record
     *
     * @param record 标签记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Tags record);

    /**
     * 插入标签记录，如果标签存在，则更新该标签为 record
     *
     * @param record 标签记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Tags record);

    /**
     * 插入标签记录（不为null的字段），如果文id存在则改为更新
     *
     * @param record 标签记录
     * @return 插入或更新成功则返回 1
     */
    int insertSelective(Tags record);

    /**
     * 根据id获取标签
     *
     * @param id id
     * @return 存在此id对应的标签则返回该标签
     */
    Tags selectByPrimaryKey(Integer id);

    /**
     * 根据id更新标签记录（不为null的字段）
     *
     * @param record 标签记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKeySelective(Tags record);

    /**
     * 根据id更新标签
     *
     * @param record 标签记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKey(Tags record);

    /**
     * 批量更新标签（根据每个id）
     *
     * @param list 标签列表
     * @return 更新成功则返回 1
     */
    int updateBatch(List<Tags> list);

    /**
     * 批量更新标签记录（根据每个id）
     *
     * @param list 标签记录
     * @return 更新成功则返回 1
     */
    int updateBatchSelective(List<Tags> list);

    /**
     * 批量插入标签（根据每个id）
     *
     * @param list 标签列表
     * @return 插入成功则返回 1
     */
    int batchInsert(@Param("list") List<Tags> list);

    Tags getIdByTagName(String tagName);

    int insertByTagName(String tagName);
}