package com.weizhiblog.service;

import com.weizhiblog.bean.*;
import com.weizhiblog.mapper.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 *
 * @createTime 07-17 19:20:9
 * @author Touka_
 * @classname com.weizhiblog.service.ArticleService
 * @lastModifiedTime 7月17日   19:20:9
 */
@Service
@Log4j2
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleTagsMapper articleTagsMapper;
    @Autowired
    TagsMapper tagsMapper;
    @Autowired
    DataMapper dataMapper;
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    CommentService commentService;


    @Transactional
    public ResponseBean insertArticle(Article article) {
        String title = article.getTitle();
        Integer cid = article.getCid();
        Integer uid = article.getUid();
        Article article1 = articleMapper.getArticleByTitleAndUid(title, uid);
        if (article1 != null) {
            return ResponseBean.builder().status(-2).message("标题重复！").object(article1).build();
        }
        User user = userMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return ResponseBean.builder().status(-4).message("用户不存在！").object(article.getUid()).build();
        }
        Category category = categoryMapper.getCategoryByCidAndUid(cid, uid);
        if (category == null) {
            return ResponseBean.builder().status(-3).message("目录不存在！").object(article.getCid()).build();
        }
        article.setPublishDate(new Timestamp(System.currentTimeMillis()));
        article.setEditTime(new Timestamp(System.currentTimeMillis()));
        article.setCommentNum(0);
        article.setLikeNum(0);
        article.setPageView(0);
        if (article.getState() != 1 && article.getState() != -1 && article.getState() != 0) {
            return ResponseBean.builder().status(-5).message("没有此状态！").build();
        }
        if (article.getPublicToOthers() != null) {
            article.setPublicToOthers(false);
        }
        return articleMapper.insert(article) == 1 ?
                ResponseBean.builder().status(1).message("添加文章成功！").object(articleMapper.selectByPrimaryKey(articleMapper.getLastInsertId())).build() :
                ResponseBean.builder().status(0).message("未知原因！").build();
    }

    @Transactional
    public ResponseBean deleteArticle(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            return ResponseBean.builder().status(-2).message("该文章不存在").object(id).build();
        }
        dataMapper.deleteDataByAid(id);
        List<Comment> comments = commentsMapper.listCommentsByAid(id);
        if (comments.size() != 0) {//删除该用户每篇文章的评论,这里可能存在删除评论出错的问题。
            //具体表现为 先删父评论时，子评论有父评论的外键。这里先不管，遇到问题再说
            for (Comment comment : comments) {
                commentsMapper.deleteByPrimaryKey(comment.getId());
            }
        }
        //删文章标签对应关系
        articleTagsMapper.deleteByAid(id);
        //评论删完了就删文章
        Article article1 = articleMapper.selectByPrimaryKey(id);
        articleMapper.deleteByPrimaryKey(id);
        return ResponseBean.builder().status(1).message("成功").object(article1).build();
    }

    @Transactional
    public ResponseBean putArticle(@NotNull Integer id, Article article) {
        Integer uid = article.getUid();
        Integer cid = article.getCid();
        String title = article.getTitle();
        @NotNull Integer articleId = article.getId();
        Integer state = article.getState();
        Article article1 = articleMapper.selectByPrimaryKey(articleId);
        if (article1 == null) {
            return ResponseBean.builder().status(-2).message("文章id不存在！").object(articleId).build();
        }
        if (!id.equals(articleId)) {
            return ResponseBean.builder().status(-8).message("id错误！").object(state).build();
        }
        if (state != null && state != 1 && state != 0 && state != -1) {
            return ResponseBean.builder().status(-4).message("没有此状态！").object(state).build();
        }
        if (uid != null && userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-5).message("不存在该用户！").object(uid).build();
        }
        if (categoryMapper.getCategoryByCidAndUid(cid, uid) == null) {
            return ResponseBean.builder().status(-7).message("没有此目录！").object(cid).build();
        }
        Article articleByTitleAndUid = articleMapper.getArticleByTitleAndUid(title, uid);
        if (articleByTitleAndUid != null && !articleByTitleAndUid.getId().equals(id)) {
            return ResponseBean.builder().status(-3).message("标题重复！").build();
        }
        article.setEditTime(new Timestamp(System.currentTimeMillis()));
        if (article.getPublishDate() == null) {
            article.setPublishDate(article1.getPublishDate());
        }
        int i = articleMapper.updateByPrimaryKey(article);
        return i == 1 ? ResponseBean.builder().status(1).message("更新成功！").object(article).build() :
                ResponseBean.builder().status(-1).message("未知错误！").build();
    }

    @Transactional
    public ResponseBean patchArticle(@NotNull Integer id, Article article) {
        Integer uid = article.getUid();
        Integer cid = article.getCid();
        String title = article.getTitle();
        Integer state = article.getState();
        Article article1 = articleMapper.selectByPrimaryKey(id);
        article.setId(id);
        if (article1 == null) {
            return ResponseBean.builder().status(-2).message("文章id不存在！").object(id).build();
        }
        if (state != null && state != 1 && state != 0 && state != -1) {
            return ResponseBean.builder().status(-4).message("没有此状态！").object(state).build();
        }
        if (uid != null && userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-5).message("不存在该用户！").object(uid).build();
        }
        if (uid != null && cid != null && categoryMapper.getCategoryByCidAndUid(cid, uid) == null) {
            return ResponseBean.builder().status(-7).message("没有此目录！").object(cid).build();
        }
        Article articleByTitleAndUid = articleMapper.getArticleByTitleAndUid(title, uid);
        if (articleByTitleAndUid != null && !articleByTitleAndUid.getId().equals(id)) {
            return ResponseBean.builder().status(-3).message("标题重复！").build();
        }
        int i = articleMapper.updateByPrimaryKeySelective(article);
        Article article2 = articleMapper.selectByPrimaryKey(id);
        return i == 1 ? ResponseBean.builder().status(1).message("更新成功！").object(article2).build() :
                ResponseBean.builder().status(-1).message("未知错误！").build();
    }


    public ResponseBean listAllArticles() {
        List<Article> articles = articleMapper.listArticles();
        if (articles == null || articles.size() == 0) {
            return ResponseBean.builder().status(-2).message("文章列表为空！").build();
        }
        Integer id;
        for (Article article : articles) {
            id = article.getId();
            ResponseBean responseBean = listAllTagsByAid(id);
            if (responseBean.getStatus() == 1) {
                article.setTags((List<Tag>) responseBean.getObject());
            }
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
    }


    public ResponseBean listAllArticlesFromUser(Integer uid) {
        User user = userMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        List<Article> articles = articleMapper.listArticlesByUid(uid);
        if (articles == null || articles.size() == 0) {
            return ResponseBean.builder().status(-3).message("文章列表为空！").object(uid).build();
        }
        Integer id;
        for (Article article : articles) {
            id = article.getId();
            ResponseBean responseBean = listAllTagsByAid(id);
            if (responseBean.getStatus() == 1) {
                article.setTags((List<Tag>) responseBean.getObject());
            }
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
    }


    public ResponseBean listAllArticlesFromTag(Integer tid) {
        if (tagsMapper.selectByPrimaryKey(tid) == null) {
            return ResponseBean.builder().status(-2).message("该标签不存在！").object(tid).build();
        }
        List<Integer> aids = articleTagsMapper.listAidsByTid(tid);
        List<Article> articles = new ArrayList<>();
        for (Integer aid : aids) {
            articles.add(articleMapper.selectByPrimaryKey(aid));
        }
        Integer id;
        for (Article article : articles) {
            id = article.getId();
            ResponseBean responseBean = listAllTagsByAid(id);
            if (responseBean.getStatus() == 1) {
                article.setTags((List<Tag>) responseBean.getObject());
            }
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
    }


    public ResponseBean listAllArticlesFromUserAndCategory(Integer uid, Integer cid) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        if (categoryMapper.selectByPrimaryKey(cid) == null) {
            return ResponseBean.builder().status(-3).message("该目录不存在！").object(cid).build();
        }
        List<Article> articles = articleMapper.listArticlesByUidAndCid(uid, cid);
        Integer id;
        for (Article article : articles) {
            id = article.getId();
            ResponseBean responseBean = listAllTagsByAid(id);
            if (responseBean.getStatus() == 1) {
                article.setTags((List<Tag>) responseBean.getObject());
            }
        }
        return articles.size() == 0 ?
                ResponseBean.builder().status(-4).message("文章列表为空！").build() :
                ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
    }


    public ResponseBean listAllArticlesFromUserAndTag(Integer uid, Integer tid) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("此用户不存在！").object(uid).build();
        }
        if (tagsMapper.selectByPrimaryKey(tid) == null) {
            return ResponseBean.builder().status(-3).message("此标签不存在").object(tid).build();
        }
        List<Article> articles = articleMapper.listArticlesByUid(uid);
        for (Article article : articles) {
            ArticleTags articleTags = articleTagsMapper.listArticleTagsByAidAndTid(article.getId(), tid);
            if (articleTags == null) {
                articles.remove(article);
            }
        }
        Integer id;
        for (Article article : articles) {
            id = article.getId();
            ResponseBean responseBean = listAllTagsByAid(id);
            if (responseBean.getStatus() == 1) {
                article.setTags((List<Tag>) responseBean.getObject());
            }
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
    }


    public ResponseBean listAllTagsByAid(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            return ResponseBean.builder().status(-2).message("该文章不存在！").object(id).build();
        }
        List<Integer> tids = articleTagsMapper.listTidsByAid(id);
        List<Tag> tags = new ArrayList<>();
        Tag tag;
        for (Integer tid : tids) {
            tag = tagsMapper.selectByPrimaryKey(tid);
            if (tag == null) {
                log.error("有标签错误，请及时处理！" + tid);
            } else {
                tags.add(tag);
            }
        }
        if (tags.size() != 0) {
            return ResponseBean.builder().status(1).message("获取成功！").object(tags).build();
        } else {
            return ResponseBean.builder().status(-3).message("标签列表为空！").build();
        }
    }


    public ResponseBean listAllCommentsByAid(Integer id) {
        return commentService.listAllCommentsByAid(id);
    }


    public ResponseBean getArticleById(Integer id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            return ResponseBean.builder().status(-2).message("文章不存在").build();
        }
        ResponseBean responseBean = listAllTagsByAid(id);
        if (responseBean.getStatus() == 1) {
            article.setTags((List<Tag>) responseBean.getObject());
        }
        ResponseBean responseBean1 = commentService.listAllCommentsByAid(id);
        if (responseBean1.getStatus()==1){
            article.setComments((List<Map<String, Object>>) responseBean1.getObject());
        }
        patchArticle(id,Article.builder().pageView(article.getPageView()+2)
                .likeNum(article.getLikeNum()+1)
                .build());
        return ResponseBean.builder().status(1).message("获取成功").object(article).build();
    }

}
