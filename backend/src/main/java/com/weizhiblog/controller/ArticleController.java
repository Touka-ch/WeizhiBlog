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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @PostMapping
    public ResponseBean insertArticle(@RequestBody Article article) {
        return articleService.insertArticle(article);
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteArticle(@PathVariable("id") Integer id) {
        return articleService.deleteArticle(id);
    }


    @PutMapping("/{id}")
    public ResponseBean putArticle(@NotNull @PathVariable Integer id,
                                   @RequestBody @Validated Article article) {
        return articleService.putArticle(id, article);
    }

    @PatchMapping("/{id}")
    public ResponseBean patchArticle(@NotNull @PathVariable Integer id,
                                     @RequestBody @Validated Article article) {
        return articleService.patchArticle(id, article);
    }

    @GetMapping
    public ResponseBean listAllArticles() {
        return articleService.listAllArticles();
    }

    @GetMapping("/user/{uid}")
    public ResponseBean listAllArticlesFromUser(@PathVariable("uid") Integer uid) {
        return articleService.listAllArticlesFromUser(uid);
    }

    @GetMapping("/tag/{tid}")
    public ResponseBean listAllArticlesFromTag(@PathVariable("tid") Integer tid) {
        return articleService.listAllArticlesFromTag(tid);
    }

    @GetMapping("/user/{uid}/category/{cid}")
    public ResponseBean listAllArticlesFromUserAndCategory(@PathVariable("uid") Integer uid,
                                                           @PathVariable("cid") Integer cid) {
        return articleService.listAllArticlesFromUserAndCategory(uid, cid);
    }

    @GetMapping("/user/{uid}/tag/{tid}")
    public ResponseBean listAllArticlesFromUserAndTag(@PathVariable("uid") Integer uid,
                                                      @PathVariable("tid") Integer tid) {
        return articleService.listAllArticlesFromUserAndTag(uid, tid);
    }

    @GetMapping("/{id}")
    public ResponseBean getArticleById(@PathVariable("id") Integer id) {
        return articleService.getArticleById(id);
    }

}
