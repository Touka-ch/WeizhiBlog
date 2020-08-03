package com.weizhiblog.service;

import com.weizhiblog.bean.Article;
import com.weizhiblog.bean.Data;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.User;
import com.weizhiblog.mapper.ArticleMapper;
import com.weizhiblog.mapper.DataMapper;
import com.weizhiblog.mapper.UserMapper;
import com.weizhiblog.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/*
 *
 * @createTime 07-22 12:44:24
 * @author Touka_
 * @classname com.weizhiblog.service.DataService
 * @lastModifiedTime 7月22日   12:44:24
 */
@Service
@Slf4j
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
        User user = userMapper.selectByPrimaryKey(uid);
        if (user == null) {
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
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
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
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
            return ResponseBean.builder().status(-2).message("该用户不存在！").object(uid).build();
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
        return getNumInNDayBuUid(uid, n, 1);
    }

    public ResponseBean getLikeNumInNDayByUserId(Integer uid, Integer n) {
        return getNumInNDayBuUid(uid, n, 2);
    }

    public ResponseBean getCommentNumInNDayByUserId(Integer uid, Integer n) {
        return getNumInNDayBuUid(uid, n, 3);
    }

    private ResponseBean getNumInNDayBuUid(Integer uid, Integer n, Integer flag) {
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
            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
                    23, 59, 59);
            Date endOfDate = calendar2.getTime();
            int pastDay = DateUtils.differentDaysByMillisecond(data.getDay(), endOfDate);
            switch (flag) {
                case 1: {
                    if (pastDay < n && pastDay >= 0) {
                        res[pastDay] += data.getPv();
                    }
                }
                case 2: {
                    if (pastDay < n && pastDay >= 0) {
                        res[pastDay] += data.getLikeNum();
                    }
                }
                case 3: {
                    if (pastDay < n && pastDay >= 0) {
                        res[pastDay] += data.getCommentNum();
                    }
                }
            }
            log.info(data.toString());
        }
        return ResponseBean.builder().status(1).message("获取成功！").object(res).build();
    }

}
