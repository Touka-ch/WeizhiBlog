package com.weizhiblog.mapper;

import com.weizhiblog.bean.Totalpvview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TotalpvviewMapper {
    /**
     * 插入文章总浏览量
     *
     * @param record 文章总浏览量记录
     * @return 插入成功则返回 1
     */
    int insert(Totalpvview record);

    /**
     * 插入文章总浏览量，如果文章总浏览量存在，则更新该文章总浏览量为 record
     *
     * @param record 文章总浏览量记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdate(Totalpvview record);

    /**
     * 插入文章总浏览量记录（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章总浏览量记录
     * @return 插入或更新成功则返回 1
     */
    int insertOrUpdateSelective(Totalpvview record);

    /**
     * 插入或更新文章总浏览量（不为null的字段），如果文章id存在则改为更新
     *
     * @param record 文章总浏览量记录
     * @return 插入成功则返回 1
     */
    int insertSelective(Totalpvview record);

    /**
     * 批量插入文章总浏览量
     *
     * @param list 文章总浏览量列表
     * @return 更新成功则返回更新的行数
     */
    int batchInsert(@Param("list") List<Totalpvview> list);
}