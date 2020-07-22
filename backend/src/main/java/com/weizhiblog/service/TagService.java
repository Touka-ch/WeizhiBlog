package com.weizhiblog.service;


import com.weizhiblog.bean.ArticleTags;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.mapper.ArticleTagsMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class TagService {
    @Autowired
    ArticleTagsMapper articleTagsMapper;

    /**
     * 给文章打上标签
     * @param articleTag 标签与文章对应关系
     * @return 是否打标签成功
     */
    public ResponseBean addTag(ArticleTags articleTag) {
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     * 给文章打上多个标签
     * @param articleTags 标签文章对应关系列表
     * @return 是否打标签成功
     */
    public ResponseBean addTags(List<ArticleTags> articleTags) {
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     *删除某个文章上的某个标签
     * @param id 文章标签对应关系id
     * @return 是否删除成功
     */
    public ResponseBean deleteTag(Integer id) {
        return ResponseBean.builder().status(1).message("").build();
    }

    /**
     *删除某个文章上的所有标签
     * @param id 文章id
     * @return 是否删除成功
     */
    public ResponseBean deleteAllTags(Integer id) {
        return ResponseBean.builder().status(1).message("").build();
    }
}
