package com.weizhiblog.service;

import com.weizhiblog.bean.Article;
import com.weizhiblog.bean.ResponseBean;
import org.springframework.stereotype.Service;

import java.util.List;

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
     */
    public ResponseBean insertArticle(Article article) {
        return ResponseBean.builder().status(1).message("").build();
    }


    /**
     * 更新文章
     *
     * @param article 文章实例
     * @return ResponseBean
     */
    public ResponseBean updateArticle(Article article) {
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 获取所有文章
     *
     * @return ResponseBean
     */
    public ResponseBean getAllArticles() {
        return ResponseBean.builder().status(1).message("").build();
    }


    public ResponseBean allTagsArticle(Integer id) {
        return null;
    }

    public ResponseBean allCommentsArticle(Integer id) {
        return null;
    }

    public ResponseBean fromTagArticle(Integer tid) {
        return null;
    }

    public ResponseBean updateArticleState(Integer id, Integer state) {
        return null;
    }

    public ResponseBean updateSelectiveArticleState(List<Integer> ids, Integer state) {
        return null;
    }

    public ResponseBean updateArticlePublicStatus(List<Integer> id, List<Integer> publicStatus) {
        return null;
    }

    public ResponseBean getAllArticlesFromUser(Integer uid) {
        return null;
    }

    public ResponseBean getAllArticlesFromUserAndCategory(Integer uid, Integer cid) {
        return null;
    }

    public ResponseBean getAllArticlesFromUserAndTag(Integer uid, Integer tid) {
        return null;
    }
}
