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
    CommentsMapper commentsMapper;

    /**
     * 添加文章
     *
     * @param article 文章实例
     * @return ResponseBean
     */
    @Transactional
    public ResponseBean insertArticle(Article article) {
        try {
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
            article.setState(1);
            article.setPageView(0);
            if (article.getPublicToOthers() != null) {
                article.setPublicToOthers(false);
            }
            return articleMapper.insert(article) == 1 ?
                    ResponseBean.builder().status(1).message("添加文章成功！").object(articleMapper.getLastInsertId()).build() :
                    ResponseBean.builder().status(0).message("未知原因！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 根据id更新文章状态
     *
     * @param id    文章id
     * @param state 文章状态
     * @return Res
     */
    public ResponseBean updateArticleState(Integer id, Integer state) {
        try {
            Article article1 = articleMapper.selectByPrimaryKey(id);
            if (article1 == null) {
                return ResponseBean.builder().status(-2).message("该id对应文章不存在！").object(id).build();
            }
            if (state != 0 && state != 1 && state != -1) {
                return ResponseBean.builder().status(-3).message("没有此状态！").object(state).build();
            }
            Article article = new Article();
            article.setId(id);
            article.setState(state);
            int i = articleMapper.updateByPrimaryKey(article);
            return i == 1 ?
                    ResponseBean.builder().status(1).message("修改成功！").build() :
                    ResponseBean.builder().status(-4).message("未发生更改！").object(id).build();

        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 批量更新文章状态
     *
     * @param ids   id集合
     * @param state 状态
     * @return Res
     */
    public ResponseBean updateSelectiveArticleState(List<Integer> ids, Integer state) {
        if (state != 0 && state != 1 && state != -1) {
            return ResponseBean.builder().status(-3).message("没有此状态！").object(state).build();
        }
        Article article = new Article();
        article.setState(state);
        try {
            for (Integer id : ids) {
                Article article1 = articleMapper.selectByPrimaryKey(id);
                if (article1 == null) {
                    return ResponseBean.builder().status(-2).message("该id对应文章不存在！").object(id).build();
                }
                article.setId(id);
                int i = articleMapper.updateByPrimaryKey(article);
                if (i != 1) {
                    return ResponseBean.builder().status(-4).message("未发生更改！").object(id).build();
                }
            }
            return ResponseBean.builder().status(1).message("修改成功！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }


    /**
     * 更新文章
     *
     * @param article 文章
     * @return ResponseBean
     */
    public ResponseBean updateArticle(Article article) {
        try {
            Integer uid = article.getUid();
            Integer cid = article.getCid();
            String title = article.getTitle();
            @NotNull Integer id = article.getId();
            Integer state = article.getState();
            if (state != null && state != 1 && state != 0 && state != -1) {
                return ResponseBean.builder().status(-4).message("没有此状态！").object(state).build();
            }
            if (uid != null && userMapper.selectByPrimaryKey(uid) == null) {
                return ResponseBean.builder().status(-5).message("不存在该用户！").object(uid).build();
            }
            if (categoryMapper.getCategoryByCidAndUid(cid, uid) == null) {
                return ResponseBean.builder().status(-7).message("没有此目录！").object(cid).build();
            }
            Article article1 = articleMapper.selectByPrimaryKey(id);
            if (article1 == null) {
                return ResponseBean.builder().status(-2).message("文章id不存在！").object(id).build();
            }
            String title1 = article1.getTitle();
            if (title.equals(title1)) {
                return ResponseBean.builder().status(-6).message("标题未改变！").object(id).build();
            }
            if (articleMapper.getArticleByTitleAndUid(title, uid) != null) {
                return ResponseBean.builder().status(-3).message("标题重复！").object(id).build();
            }
            int i = articleMapper.updateByPrimaryKeySelective(article);
            return i == 1 ? ResponseBean.builder().status(1).message("更新成功！").build() :
                    ResponseBean.builder().status(-1).message("未知错误！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * @param id           文章id
     * @param publicStatus 文章公开状态
     * @return Res
     */
    public ResponseBean updateArticlePublicStatus(Integer id, Boolean publicStatus) {
        try {
            Article article = articleMapper.selectByPrimaryKey(id);
            if (article == null) {
                return ResponseBean.builder().status(-2).message("该文章id不存在！").object(id).build();
            }
            if (article.getPublicToOthers() == publicStatus) {
                return ResponseBean.builder().status(-3).message("公开状态未改变！").object(id).build();
            }
            article.setPublicToOthers(publicStatus);
            int i = articleMapper.updateByPrimaryKey(article);
            return i == 1 ? ResponseBean.builder().status(1).message("修改成功！").build() :
                    ResponseBean.builder().status(0).message("未知错误！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }


    /**
     * 获取所有文章
     *
     * @return Res
     */
    public ResponseBean getAllArticles() {
        try {
            List<Article> articles = articleMapper.listArticles();
            if (articles.size() == 0) {
                return ResponseBean.builder().status(-2).message("文章列表为空！").build();
            }
            return ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 查询该用户所有文章
     *
     * @param uid 用户id
     * @return Res
     */
    public ResponseBean getAllArticlesFromUser(Integer uid) {
        try {
            User user = userMapper.selectByPrimaryKey(uid);
            if (user == null) {
                return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
            }
            List<Article> articles = articleMapper.listArticlesByUid(uid);
            if (articles.size() == 0) {
                return ResponseBean.builder().status(-3).message("文章列表为空！").object(uid).build();
            }
            return ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 获取有某标签的文章列表
     *
     * @param tid 标签id
     * @return Res
     */
    public ResponseBean fromTagArticle(Integer tid) {
        try {
            if (tagsMapper.selectByPrimaryKey(tid) == null) {
                return ResponseBean.builder().status(-2).message("该标签不存在！").object(tid).build();
            }
            List<Integer> aids = articleTagsMapper.listAidsByTid(tid);
            List<Article> articles = new ArrayList<>();
            for (Integer aid : aids) {
                articles.add(articleMapper.selectByPrimaryKey(aid));
            }
            return ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 根据用户id和目录id找出文章，即某用户的某目录的文章列表
     *
     * @param uid 用户id
     * @param cid 目录id
     * @return Res
     */
    public ResponseBean getAllArticlesFromUserAndCategory(Integer uid, Integer cid) {
        try {
            if (userMapper.selectByPrimaryKey(uid) == null) {
                return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
            }
            if (categoryMapper.selectByPrimaryKey(cid) == null) {
                return ResponseBean.builder().status(-3).message("该目录不存在！").object(cid).build();
            }
            List<Article> articles = articleMapper.listArticlesByUidAndCid(uid, cid);
            return articles.size() == 0 ? ResponseBean.builder().status(-4).message("文章列表为空！").build() :
                    ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 获取某用户打了某标签的所有文章
     *
     * @param uid 用户id
     * @param tid 标签id
     * @return Res
     */
    public ResponseBean getAllArticlesFromUserAndTag(Integer uid, Integer tid) {
        try {
            if (userMapper.selectByPrimaryKey(uid) == null) {
                return ResponseBean.builder().status(-2).message("此用户不存在！").object(uid).build();
            }
            if (tagsMapper.selectByPrimaryKey(tid) == null) {
                return ResponseBean.builder().status(-3).message("此标签不存在").object(tid).build();
            }
            List<Article> articles = articleMapper.listArticlesByUid(uid);
            List<Integer> aids = articleTagsMapper.listAidsByTid(tid);
            List<Integer> uaids = new ArrayList<>();
            for (Article article : articles) {
                uaids.add(article.getUid());
            }
            uaids.retainAll(aids);
            articles.clear();
            for (Integer uaid : uaids) {
                articles.add(articleMapper.selectByPrimaryKey(uaid));
            }
            return ResponseBean.builder().status(1).message("获取成功！").object(articles).build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 获取该文章所有标签
     *
     * @param id 文章id
     * @return Res
     */
    public ResponseBean allTagsArticle(Integer id) {
        try {
            Article article = articleMapper.selectByPrimaryKey(id);
            if (article == null) {
                return ResponseBean.builder().status(-2).message("该用户不存在！").object(id).build();
            }
            List<Integer> tids = articleTagsMapper.listTidsByAid(id);
            List<Tags> tags = new ArrayList<>();
            Tags tag;
            for (Integer tid : tids) {
                tag = tagsMapper.selectByPrimaryKey(tid);
                if (tag == null) {
                    log.error("有标签错误，请及时处理！" + tid);
                } else {
                    tags.add(tag);
                }
            }
            return ResponseBean.builder().status(1).message("获取成功！").object(tags).build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 获取该文章所有评论
     *
     * @param id 文章id
     * @return Res
     */
    public ResponseBean allCommentsArticle(Integer id) {
        try {
            Article article = articleMapper.selectByPrimaryKey(id);
            if (article == null) {
                return ResponseBean.builder().status(-2).message("该文章不存在！").object(id).build();
            }
            List<Comments> comments = commentsMapper.listCommentsByAid(id);
            return comments.size() == 0 ?
                    ResponseBean.builder().status(-3).message("评论列表为空！").build() :
                    ResponseBean.builder().status(1).message("获取成功！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }


}
