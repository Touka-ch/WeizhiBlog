package com.weizhiblog.mapper;

import com.weizhiblog.bean.Pv;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PvMapper {
    /**
     * 根据文章id删除文章的浏览量
     *
     * @param id 文章id
     * @return 删除成功则返回 1
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入文章的浏览量
     *
     * @param record 文章浏览量记录
     * @return 插入成功则返回 1
     */
    int insert(Pv record);

    /**
     * 插入文章浏览量，如果文章浏览量存在，则更新该文章浏览量为 record
     *
     * @param record 文章浏览量记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Pv record);

    /**
     * 插入文章浏览量记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章浏览量记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Pv record);

    /**
     * 插入或更新文章浏览量记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章浏览量记录
     * @return 插入成功则返回 1
     */
    int insertSelective(Pv record);

    /**
     * 根据文章id获取文章浏览量
     *
     * @param id 文章id
     * @return 存在此id对应的文章浏览量则返回该文章浏览量
     */
    Pv selectByPrimaryKey(Integer id);

    /**
     * 根据文章的id更新文章浏览量（不为null的字段）
     *
     * @param record 文章浏览量记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKeySelective(Pv record);

    /**
     * 根据文章的id更新文章浏览量
     *
     * @param record 文章浏览量记录
     * @return 更新成功则返回 1
     */
    int updateByPrimaryKey(Pv record);

    /**
     * 批量更新文章浏览量（根据每个文章的id）
     *
     * @param list 文章浏览量列表
     * @return 更新成功则返回 1
     */
    int updateBatch(List<Pv> list);

    /**
     * 批量更新文章浏览量记录（根据每个文章的id）
     *
     * @param list 文章浏览量列表
     * @return 更新成功则返回 1
     */
    int updateBatchSelective(List<Pv> list);

    /**
     * 批量插入文章浏览量
     *
     * @param list 文章浏览量列表
     * @return 更新成功则返回 1
     */
    int batchInsert(@Param("list") List<Pv> list);
}