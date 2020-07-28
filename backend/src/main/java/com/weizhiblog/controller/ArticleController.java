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

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    /**
     * 添加一篇文章
     *
     * @param article 文章对象
     * @return ResponseBean
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean insertArticle(@RequestBody Article article) {
        return articleService.insertArticle(article);
    }

    /**
     * 修改文章的存在状态 1为正常、2为删除、0为草稿
     *
     * @param id    文章id
     * @param state 文章存在状态
     * @return ResponseBean
     */
    @RequestMapping(value = "/state", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean updateArticleState(@RequestParam("id") Integer id,
                                           @RequestParam("state") Integer state) {
        return articleService.updateArticleState(id, state);
    }

    /**
     * 批量修改文章的存在状态
     *
     * @param ids   文章id的集合
     * @param state 文章存在状态
     * @return ResponseBean
     */
    @RequestMapping(value = "/state/selective", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean updateSelectiveArticleState(@RequestParam("ids") List<Integer> ids,
                                                    @RequestParam("state") Integer state) {
        return articleService.updateSelectiveArticleState(ids, state);
    }

    /**
     * 修改文章
     *
     * @param article 文章
     * @return ResponseBean
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    public ResponseBean updateArticle(@RequestBody @Validated Article article) {
        return articleService.updateArticle(article);
    }

    /**
     * 设置文章的公开状态是私密还是公开
     *
     * @param id           文章id
     * @param publicStatus 文章公开状态
     * @return ResponseBean
     */
    @RequestMapping(value = "/publicStatus", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean updateArticlePublicStatus(@RequestParam("id") Integer id,
                                                  @RequestParam("publicStatus") Boolean publicStatus) {
        return articleService.updateArticlePublicStatus(id, publicStatus);
    }

    /**
     * 获取所有文章
     *
     * @return ResponseBean
     */
    @RequestMapping(value = "/all", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getAllArticles() {
        return articleService.getAllArticles();
    }

    /**
     * 获取某用户所有文章
     *
     * @param uid 用户id
     * @return ResponseBean
     */
    @RequestMapping(value = "/fromUser", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getAllArticlesFromUser(@RequestParam("uid") Integer uid) {
        return articleService.getAllArticlesFromUser(uid);
    }

    /**
     * 获取打了该标签的所有文章
     *
     * @param tid 标签id
     * @return ResponseBean
     */
    @RequestMapping(value = "/fromTag", method = {RequestMethod.POST})
    public ResponseBean getAllArticlesFromTag(@RequestParam("tid") Integer tid) {
        return articleService.fromTagArticle(tid);
    }

    /**
     * 获取某用户某目录的所有文章
     *
     * @param uid 用户id
     * @param cid 目录id
     * @return ResponseBean
     */
    @RequestMapping(value = "/fromUserCategory", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getAllArticlesFromUserAndCategory(@RequestParam("uid") Integer uid,
                                                          @RequestParam("cid") Integer cid) {
        return articleService.getAllArticlesFromUserAndCategory(uid, cid);
    }

    /**
     * 获取某用户打了某标签
     *
     * @param uid 用户id
     * @param tid 标签id
     * @return ResponseBean
     */
    @RequestMapping(value = "/fromUserTag", method = {RequestMethod.POST})
    public ResponseBean getAllArticlesFromUserAndTag(@RequestParam("uid") Integer uid,
                                                     @RequestParam("tid") Integer tid) {
        return articleService.getAllArticlesFromUserAndTag(uid, tid);
    }

    /**
     * 获取某文章的所有标签
     *
     * @param id 文章id
     * @return ResponseBean
     */
    @RequestMapping(value = "/allTags", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getAllTagsFromArticle(@RequestParam("id") Integer id) {
        return articleService.allTagsArticle(id);
    }

    /**
     * 获取某文章的所评论
     *
     * @param id 文章id
     * @return ResponseBean
     */
    @RequestMapping(value = "/allComments", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean getAllCommentsFromArticle(@RequestParam("id") Integer id) {
        return articleService.allCommentsArticle(id);
    }

}
