package com.weizhiblog.service;


import com.weizhiblog.bean.ArticleTags;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.Tags;
import com.weizhiblog.exception.MyRuntimeException;
import com.weizhiblog.mapper.ArticleMapper;
import com.weizhiblog.mapper.ArticleTagsMapper;
import com.weizhiblog.mapper.TagsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TagService {
    @Autowired
    ArticleTagsMapper articleTagsMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagsMapper tagsMapper;

    /**
     * @param tag 内容
     * @param aid 文章id
     * @return Res
     */
    @Transactional
    public ResponseBean addTag(String tag, Integer aid) {
        if (articleMapper.selectByPrimaryKey(aid) == null) {
            return ResponseBean.builder().status(-2).message("文章不存在！").build();
        }
        List<Integer> tids = articleTagsMapper.listTidsByAid(aid);
        Tags sqlTag = tagsMapper.getIdByTagName(tag);
        if (sqlTag == null) {
            tagsMapper.insert(Tags.builder().tagName(tag).build());
            sqlTag = tagsMapper.getIdByTagName(tag);
        } else {
            for (Integer tid : tids) {
                if (tid.equals(sqlTag.getId())) {
//                    throw new MyRuntimeException(ResponseBean.builder().status(-3).message("标签不得重复！").build());
                    return ResponseBean.builder().status(-3).message("标签不得重复！").build();
                }
            }
        }
        Integer id = sqlTag.getId();
        articleTagsMapper.insert(ArticleTags.builder().aid(aid).tid(id).build());
        return ResponseBean.builder().status(1).message("成功！").build();
    }

    /**
     * @param tags 标签列表
     * @param aid  文章id
     * @return Res
     */
    @Transactional
    public ResponseBean addTags(List<String> tags, Integer aid) {
        if (articleMapper.selectByPrimaryKey(aid) == null) {
            return ResponseBean.builder().status(-2).message("文章不存在！").build();
        }
        for (String tag : tags) {
            ResponseBean responseBean = addTag(tag, aid);
            if (responseBean.getStatus() != 1) {
                throw new MyRuntimeException(responseBean);
            }
        }
        return ResponseBean.builder().status(1).message("添加成功！").build();
    }

    /**
     * @param tid 标签id
     * @param aid 文章id
     * @return Res
     */
    @Transactional
    public ResponseBean deleteTag(Integer tid, Integer aid) {
        if (articleMapper.selectByPrimaryKey(aid) == null) {
            return ResponseBean.builder().status(-2).message("文章不存在！").object(aid).build();
        }
        if (tagsMapper.selectByPrimaryKey(tid) == null) {
            return ResponseBean.builder().status(-3).message("标签不存在！").object(tid).build();
        }
        ArticleTags articleTags = articleTagsMapper.listArticleTagsByAidAndTid(aid, tid);
        if (articleTags == null) {
            return ResponseBean.builder().status(-4).message("该文章没有此标签！").build();
        }
        Integer id = articleTags.getId();
        int i = articleTagsMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return ResponseBean.builder().status(1).message("删除成功！").build();
        } else {
            throw new MyRuntimeException(ResponseBean.builder().status(0).message("数据库错误！").build());
        }
    }

    /**
     * 删除某个文章上的所有标签
     *
     * @param aid 文章id
     * @return 是否删除成功
     */
    @Transactional
    public ResponseBean deleteAllTags(Integer aid) {
        if (articleMapper.selectByPrimaryKey(aid) == null) {
            return ResponseBean.builder().status(-2).message("文章不存在！").object(aid).build();
        }
        List<Integer> tids = articleTagsMapper.listTidsByAid(aid);
        for (Integer tid : tids) {
            if (tagsMapper.selectByPrimaryKey(tid) == null) {
                return ResponseBean.builder().status(-3).message("标签不存在！").object(tid).build();
            }
        }
        for (Integer tid : tids) {
            ResponseBean responseBean = deleteTag(tid, aid);
            if (responseBean.getStatus() != 1) {
                throw new MyRuntimeException(responseBean);
            }
        }
        return ResponseBean.builder().status(1).message("删除成功").build();
    }

}
