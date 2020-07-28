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
     *
     * @param category 目录信息
     * @return 是否新建成功
     * 关于 status
     * 1：新建成功
     * 0：未知错误
     * -2：目录id已存在
     * -3：目录名称已存在
     */
    public ResponseBean insertCategory(Category category) {
        try {
            if (categoryMapper.selectByPrimaryKey(category.getId()) != null)
                return ResponseBean.builder().status(-2).message("目录id已存在").build();
            if (categoryMapper.getCategoryByCateName(category.getCateName()) != null)
                return ResponseBean.builder().status(-3).message("目录名称已存在").build();
            return categoryMapper.insert(category) == 1 ?
                    ResponseBean.builder().status(1).message("创建成功").build() :
                    ResponseBean.builder().status(0).message("未知原因！").build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 删除指定目录
     *
     * @param id 目录id
     * @return 是否删除成功
     * 关于 status
     * 1： 删除成功
     * 0：未知错误
     * -2：删除目录不存在
     */
    public ResponseBean deleteCategory(int id) {
        try {
            if (categoryMapper.selectByPrimaryKey(id) == null)
                return ResponseBean.builder().status(-2).message("目录不已存在").build();
            return categoryMapper.deleteByPrimaryKey(id) == 1 ?
                    ResponseBean.builder().status(1).message("删除成功").build() :
                    ResponseBean.builder().status(0).message("未知原因！").build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 修改目录名称
     *
     * @param category 目录信息
     * @return 是否修改成功
     * 关于 status
     * 1：更新成功
     * 0：未知原因
     * -2：id已使用
     * -3：目录名称已使用
     */
    public ResponseBean updateCategory(Category category) {
        try {
            if (categoryMapper.selectByPrimaryKey(category.getId()) != null)
                return ResponseBean.builder().status(-2).message("目录id已使用").build();
            if (categoryMapper.getCategoryByCateName(category.getCateName()) != null)
                return ResponseBean.builder().status(-3).message("目录名称已存在").build();
            return categoryMapper.updateByPrimaryKey(category) == 1 ?
                    ResponseBean.builder().status(1).message("更新成功").build() :
                    ResponseBean.builder().status(0).message("未知原因！").build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 找出用户在指定目录下所有文章
     *
     * @param uid 用户id
     * @param cid 目录id
     * @return 是否查询成功
     */
    public ResponseBean getArticleByCategory(int uid, int cid) {
        return ResponseBean.builder().status(1).message("").build();
    }

}
