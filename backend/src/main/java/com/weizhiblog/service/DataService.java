package com.weizhiblog.service;

import com.weizhiblog.bean.Article;
import com.weizhiblog.bean.Data;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.mapper.ArticleMapper;
import com.weizhiblog.mapper.DataMapper;
import com.weizhiblog.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/*
 *
 * @createTime 07-22 12:44:24
 * @author Touka_
 * @classname com.weizhiblog.service.DataService
 * @lastModifiedTime 7月22日   12:44:24
 */
@Service
public class DataService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    DataMapper dataMapper;

    /**
     * 返回该用户文章总浏览量
     *
     * @param uid 用户id
     * @return Res
     */
    public ResponseBean getPvNumByUserId(Integer uid) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        List<Article> articles = articleMapper.listArticlesByUid(uid);
        Integer pv = 0;
        for (Article article : articles) {
            pv += article.getPageView();
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(pv).build();
    }

    /**
     * 返回该用户文章总被点赞数
     *
     * @param uid 用户id
     * @return Res
     */
    public ResponseBean getLikeNumByUserId(Integer uid) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        List<Article> articles = articleMapper.listArticlesByUid(uid);
        Integer lm = 0;
        for (Article article : articles) {
            lm += article.getLikeNum();
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(lm).build();
    }

    /**
     * 返回该用户文章总评论数
     *
     * @param uid 用户id
     * @return Res
     */
    public ResponseBean getCommentNumByUserId(Integer uid) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        List<Article> articles = articleMapper.listArticlesByUid(uid);
        Integer cm = 0;
        for (Article article : articles) {
            cm += article.getCommentNum();
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(cm).build();
    }

    /**
     * @param uid 用户id
     * @param n   天数
     * @return Res
     */
    public ResponseBean getPvNumInNDayByUserId(Integer uid, Integer n) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        if (n > 100) {
            return ResponseBean.builder().status(-3).message("超过最大支持天数！").object(n).build();
        }
        int[] res = new int[n];
        List<Data> datas = dataMapper.listDatasByUid(uid);
        Collections.reverse(datas);
        for (Data data : datas) {
            long time = data.getDay().getTime();
            long now = System.currentTimeMillis();
            int pastDay = (int) ((now - time) % 1000 * 3600 * 24 + 1);
            if (pastDay < n && pastDay > 0 && res[n] == 0) {
                res[pastDay] = data.getPv();
            }
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(res).build();
    }

    public ResponseBean getLikeNumInNDayByUserId(Integer uid, Integer n) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        if (n > 100) {
            return ResponseBean.builder().status(-3).message("超过最大支持天数！").object(n).build();
        }
        int[] res = new int[n];
        List<Data> datas = dataMapper.listDatasByUid(uid);
        Collections.reverse(datas);
        for (Data data : datas) {
            long time = data.getDay().getTime();
            long now = System.currentTimeMillis();
            int pastDay = (int) ((now - time) % 1000 * 3600 * 24 + 1);
            if (pastDay < n && pastDay > 0 && res[n] == 0) {
                res[pastDay] = data.getLikeNum();
            }
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(res).build();
    }

    public ResponseBean getCommentNumInNDayByUserId(Integer uid, Integer n) {
        if (userMapper.selectByPrimaryKey(uid) == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
        }
        if (n > 100) {
            return ResponseBean.builder().status(-3).message("超过最大支持天数！").object(n).build();
        }
        int[] res = new int[n];
        List<Data> datas = dataMapper.listDatasByUid(uid);
        Collections.reverse(datas);
        for (Data data : datas) {
            long time = data.getDay().getTime();
            long now = System.currentTimeMillis();
            int pastDay = (int) ((now - time) % 1000 * 3600 * 24 + 1);
            if (pastDay < n && pastDay > 0 && res[n] == 0) {
                res[pastDay] = data.getCommentNum();
            }
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(res).build();
    }
}
