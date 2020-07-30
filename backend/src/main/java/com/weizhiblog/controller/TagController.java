package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.TagService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
     * @param tag 标签内容
     * @param aid 文章id
     * @return Res
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ResponseBean tagAdd(@NotNull @RequestParam("tag") String tag,
                               @NotNull @RequestParam("aid") Integer aid) {
        return tagService.addTag(tag, aid);
    }


    @RequestMapping(value = "/add/multiple", method = {RequestMethod.POST})
    public ResponseBean addMultipleTags(@RequestParam("tags") List<String> tags,
                                        @RequestParam("aid") Integer aid) {
        return tagService.addTags(tags, aid);
    }


    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseBean deleteTagByAidNidTid(@RequestParam("aid") Integer aid,
                                             @RequestParam("tid") Integer tid) {
        return tagService.deleteTag(tid, aid);
    }

    /**
     * 删除某文章所有标签
     *
     * @param aid 文章id
     * @return 是否删除成功
     */
    @RequestMapping(value = "/deleteAll", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseBean deleteAllTagsByAid(@NotNull Integer aid) {
        return tagService.deleteAllTags(aid);
    }


}
