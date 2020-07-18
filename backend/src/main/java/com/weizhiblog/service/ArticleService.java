package com.weizhiblog.service;

import com.weizhiblog.bean.Article;
import com.weizhiblog.bean.ResponseBean;
import org.springframework.stereotype.Service;

/*
 *
 * @createTime 07-17 19:20:9
 * @author Touka_
 * @classname com.weizhiblog.service.ArticleService
 * @lastModifiedTime 7月17日   19:20:9
 */
@Service
public class ArticleService {
    /**
     * 添加文章
     *
     * @param article 文章实例
     * @return ResponseBean
     * 关于 status
     * 1：添加成功
     * 0：未知错误
     * -1：服务器错误
     */
    public ResponseBean insertArticle(Article article) {
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 根据id获取文章
     * @param id 文章id
     * @return ResponseBean
     * 关于status
     * 1：获取成功。并且在Obj中封装article
     * 0：未知错误
     * -1：服务器错误
     * -2：不存在此id的文章
     */
    public ResponseBean getArticleById(Integer id){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 更新文章
     * @param article 文章实例
     * @return ResponseBean
     * 关于 status
     * 1：更新成功。
     * 0：未知错误。
     * -1：服务器错误。
     */
    public ResponseBean updateArticle(Article article){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 获取所有文章
     * @return ResponseBean
     * 关于 status
     * 1：获取成功。并在第三个参数封装 List<Article> articles
     * 0：未知错误
     * -1：服务器错误
     */
    public ResponseBean getAllArticles(){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 删除id对应的文章
     * @param id 文章id
     * @return ResponseBean
     * 关于 status
     * 1：删除成功（state置为 2）
     * 0：未知错误
     * -1：服务器错误
     * -2：不存在
     */
    public ResponseBean deleteArticle(Integer id){
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 将文章放入回收站
     * @param id 文章id
     * @return ResponseBean
     * 关于 status
     * 1：回收成功（state置为 1）
     * 0：未知错误
     * -1：服务器错误
     * -2：不存在
     */
    public ResponseBean dropArticle(Integer id){
        return ResponseBean.builder().status(1).message("").build();
    }
}
