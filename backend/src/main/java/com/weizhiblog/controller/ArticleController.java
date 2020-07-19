package com.weizhiblog.controller;

/*
 * 此类用于处理关于文章的业务的转发
 *
 * @createTime 07-17 19:17:27
 * @author Touka_
 * @classname com.weizhiblog.controller.ArticleController
 * @lastModifiedTime 7月17日   19:17:27
 */

import com.weizhiblog.bean.Article;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 添加文章
     * @param article 文章实例
     * @return 添加结果
     */
    @RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.PUT})
    public ResponseBean insertArticle(@RequestBody Article article) {
        return articleService.insertArticle(article);
    }
}
