package com.weizhiblog.mapper;

import com.weizhiblog.bean.Pvview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PvviewMapper {
    /**
     * 插入文章每日浏览量
     *
     * @param record 文章每日浏览量记录
     * @return 插入成功则返回 1
     */
    int insert(Pvview record);

    /**
     * 插入文章每日浏览量，如果文章每日浏览量存在，则更新该文章每日浏览量为 record
     *
     * @param record 文章每日浏览量记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Pvview record);

    /**
     * 插入文章每日浏览量记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章每日浏览量记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Pvview record);

    /**
     * 插入或更新文章每日浏览量记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章每日浏览量记录
     * @return 插入成功则返回 1
     */
    int insertSelective(Pvview record);

    /**
     * 批量插入文章每日浏览量
     *
     * @param list 文章每日浏览量列表
     * @return 更新成功则返回 1
     */
    int batchInsert(@Param("list") List<Pvview> list);
}