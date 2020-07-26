package com.weizhiblog.service;


import com.weizhiblog.bean.ArticleTags;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.exception.MyRuntimeException;
import com.weizhiblog.mapper.ArticleMapper;
import com.weizhiblog.mapper.ArticleTagsMapper;
import com.weizhiblog.mapper.TagsMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class TagService {
    @Autowired
    ArticleTagsMapper articleTagsMapper;
    ArticleMapper articleMapper;
    TagsMapper tagsMapper;

    /**
     * 给文章打上标签
     * @param articleTag 标签与文章对应关系
     * @return 是否打标签成功
     */
    public ResponseBean addTag(ArticleTags articleTag) {
        try{
            if(articleTagsMapper.selectByPrimaryKey(articleTag.getId())!=null)
                return ResponseBean.builder().status(-2).message("标签id已存在").build();
            if(articleTagsMapper.selectByUidTid(articleTag.getAid(),articleTag.getTid())!=null)
                return ResponseBean.builder().status(-3).message("标签已存在").build();
            return articleTagsMapper.insert(articleTag)==1?
                    ResponseBean.builder().status(1).message("添加成功").build():
                    ResponseBean.builder().status(0).message("未知原因！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     * 给文章打上多个标签
     * @param articleTags 标签文章对应关系列表
     * @return 是否打标签成功
     */
    public ResponseBean addTags(List<ArticleTags> articleTags) {
        try {
            for (ArticleTags articleTag : articleTags) {
                if (articleTagsMapper.selectByPrimaryKey(articleTag.getId()) != null) {
                    throw new MyRuntimeException(-2, "标签id已存在！");
                }
                if (articleTagsMapper.insert(articleTag) != 1) {
                    throw new MyRuntimeException(0, "未知错误！");
                }
            }
            return ResponseBean.builder().status(1).message("添加成功！").build();
        } catch (Exception e) {
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     *删除某个文章上的某个标签
     * @param id 文章标签对应关系id
     * @return 是否删除成功
     */
    public ResponseBean deleteTag(Integer id) {
        try{
            if(articleTagsMapper.selectByPrimaryKey(id)==null)
                return ResponseBean.builder().status(-2).message("标签不存在").build();
            return articleTagsMapper.deleteByPrimaryKey(id)==1?
                    ResponseBean.builder().status(1).message("删除成功").build():
                    ResponseBean.builder().status(0).message("未知原因！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    /**
     *删除某个文章上的所有标签
     * @param id 文章id
     * @return 是否删除成功
     */
    public ResponseBean deleteAllTags(Integer id) {
        try{
            if(articleTagsMapper.selectByAid(id)==null)
                return ResponseBean.builder().status(-2).message("文章无标签").build();
            if(articleMapper.selectByPrimaryKey(id)==null)
                return ResponseBean.builder().status(-2).message("文章不存在").build();
            return articleTagsMapper.deleteByAid(id)==1?
                    ResponseBean.builder().status(1).message("删除成功").build():
                    ResponseBean.builder().status(0).message("未知原因！").build();
        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }

    public ResponseBean getTagId(String tagName) {
        try{
            if(tagsMapper.getIdByTagName(tagName)==null){
                tagsMapper.insertByTagName(tagName);
                return ResponseBean.builder().status(-2).message("标签不存在，已新建").object(tagsMapper.getIdByTagName(tagName).getId()).build();
            }
            else
                return ResponseBean.builder().status(-2).message("返回成功").object(tagsMapper.getIdByTagName(tagName).getId()).build();

        }catch (Exception e){
            e.printStackTrace();
            return ResponseBean.builder().status(-1).message("服务器错误！").build();
        }
    }
}
