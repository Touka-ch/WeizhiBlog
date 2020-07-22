package com.weizhiblog.service;

import com.weizhiblog.bean.Category;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.mapper.CategoryMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Log4j2
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 新建目录
     * @param category 目录信息
     * @return 是否新建成功
     * 关于 status
     * 1：新建成功
     * 0：未知错误
     * -2：目录id已存在
     * -3：目录名称已存在
     */
    public ResponseBean insertCategory(Category category) {
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 删除指定目录
     * @param id 目录id
     * @return 是否删除成功
     * 关于 status
     * 1： 删除成功
     * 0：未知错误
     * -2：删除目录不存在
     */
    public ResponseBean deleteCategory(int id) {
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 修改目录名称
     * @param category 目录信息
     * @return 是否修改成功
     */
    public ResponseBean updateCategory(Category category) {
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 找出用户在指定目录下所有文章
     * @param uid 用户id
     * @param cid 目录id
     * @return 是否查询成功
     */
    public ResponseBean getArticleByCategory(int uid,int cid) {
        return ResponseBean.builder().status(1).message("").build();
    }

}
