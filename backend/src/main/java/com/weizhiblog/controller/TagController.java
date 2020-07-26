package com.weizhiblog.controller;

import com.weizhiblog.bean.ArticleTags;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.TagService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tag")
public class TagController {
    @Autowired
    TagService tagService;

    /**
     * 给文章打标签
     * @param articleTag 文章标签对应关系
     * @return 是否打标签成功
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ResponseBean tagAdd(@RequestBody @Validated ArticleTags articleTag) {
        return tagService.addTag(articleTag);
    }

    /**
     * 给文章打多个标签
     * @param articleTags 文章标签对应关系表
     * @return 是否打标签成功
     */
    @RequestMapping(value = "/addaddmutiple", method = {RequestMethod.POST})
    public ResponseBean tagAdds(@RequestBody @Validated List<ArticleTags> articleTags) {
        return tagService.addTags(articleTags);
    }

    /**
     * 删除某文章某个标签
     * @param id 文章标签对应关系id
     * @return 是否删除成功
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.POST,RequestMethod.DELETE})
    public ResponseBean tagDelete(@NotNull Integer id) {
        return tagService.deleteTag(id);
    }

    /**
     * 删除某文章所有标签
     * @param id 文章id
     * @return 是否删除成功
     */
    @RequestMapping(value = "/deleteall", method = {RequestMethod.POST,RequestMethod.DELETE})
    public ResponseBean tagDeleteAll(@NotNull Integer id) {
        return tagService.deleteAllTags(id);
    }

    @RequestMapping(value = "/id", method = {RequestMethod.POST,RequestMethod.GET})
    public ResponseBean getID(@NotNull String tagName) {
        return tagService.getTagId(tagName);
    }

}
