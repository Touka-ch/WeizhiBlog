package com.weizhiblog.service;

import com.weizhiblog.bean.Article;
import com.weizhiblog.bean.Category;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.exception.MyRuntimeException;
import com.weizhiblog.mapper.ArticleMapper;
import com.weizhiblog.mapper.CategoryMapper;
import com.weizhiblog.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Log4j2
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleService articleService;

    /**
     * 新建目录
     *
     * @param category 目录信息
     * @return 是否新建成功
     * 关于 status
     * 1：新建成功
     * 0：未知错误
     * -2：该用户不存在
     * -3：该用户已存在此目录
     */
    public ResponseBean addCategory(Category category) {
        Integer uid = category.getUid();
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在").object(uid).build();
        }
        String cateName = category.getCateName();
        Category sqlCategory = categoryMapper.getCategoryByUidAndCateName(uid, cateName);
        if (sqlCategory != null) {
            return ResponseBean.builder().status(-3).message("该用户已存在此目录").object(sqlCategory).build();
        }
        category.setDate(new Date(System.currentTimeMillis()));
        int i = categoryMapper.insert(category);
        sqlCategory = categoryMapper.getCategoryByUidAndCateName(uid, cateName);
        if (i == 1) {
            return ResponseBean.builder().status(1).message("新建成功").object(sqlCategory).build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("数据库错误！").object(uid).build());
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
    @Transactional
    public ResponseBean deleteCategory(int id) {
        Category category = categoryMapper.selectByPrimaryKey(id);
        if (category == null) {
            return ResponseBean.builder().status(-2).message("该目录不存在").object(id).build();
        }
        Integer uid = category.getUid();
        List<Article> articles = articleMapper.listArticlesByUidAndCid(uid, id);
        for (Article article : articles) {
            ResponseBean responseBean = articleService.deleteArticle(article.getId());
            if (responseBean.getStatus() != 1) {
                throw new MyRuntimeException(responseBean);
            }
        }
        int i = categoryMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return ResponseBean.builder().status(1).message("删除成功").object(category).build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("数据库错误！").build());
        }
    }

    /**
     * 修改目录
     *
     * @param category 目录信息
     * @return 是否修改成功
     * 关于 status
     * 1：更新成功
     * 0：未知原因
     * -2：该目录不存在
     * -3：未发生改变
     * -4：目录名称重复
     */
    public ResponseBean updateCategory(Category category) {
        String cateName = category.getCateName();
        Integer id = category.getId();
        Integer uid = category.getUid();
        category.setDate(null);
        category.setUid(null);
        Category sqlCategory = categoryMapper.selectByPrimaryKey(id);
        if (sqlCategory == null) {
            return ResponseBean.builder().status(-2).message("该目录不存在").object(id).build();
        }
        if (sqlCategory.getCateName().equals(cateName)) {
            return ResponseBean.builder().status(-3).message("未发生改变！").object(id).build();
        }
        Category categoryByUidAndCateName = categoryMapper.getCategoryByUidAndCateName(uid, cateName);
        if (categoryByUidAndCateName != null) {
            return ResponseBean.builder().status(-4).message("目录名称重复！！").object(id).build();
        }
        int i = categoryMapper.updateByPrimaryKeySelective(category);
        if (i == 1) {
            return ResponseBean.builder().status(1).message("更新成功").object(category).build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("数据库错误！").object(uid).build());
        }
    }

    /**
     * 找出用户在指定目录下所有文章
     *
     * @param uid 用户id
     * @param cid 目录id
     * @return 是否查询成功
     */
    public ResponseBean addCategory(int uid, int cid) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在").object(uid).build();
        }
        if (categoryMapper.selectByPrimaryKey(cid) == null) {
            return ResponseBean.builder().status(-3).message("该目录不存在").object(cid).build();
        }
        List<Article> articles = articleMapper.listArticlesByUidAndCid(uid, cid);
        if (articles == null || articles.size() == 0) {
            return ResponseBean.builder().status(-4).message("该目录为空目录").object(cid).build();
        } else {
            return ResponseBean.builder().status(1).message("成功！").object(articles).build();
        }
    }

    /**
     * @param uid uid
     * @return Res
     */
    public ResponseBean listAllCategoriesByUid(Integer uid) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("不存在该用户").object(uid).build();
        }
        List<Category> categories = categoryMapper.listCategoriesByUid(uid);
        return categories == null || categories.size() == 0 ?
                ResponseBean.builder().status(-3).message("该用户目录为空").build() :
                ResponseBean.builder().status(1).message("获取成功！").object(categories).build();
    }
}
