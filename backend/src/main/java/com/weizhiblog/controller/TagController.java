package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.dto.TagDTO;
import com.weizhiblog.service.ArticleService;
import com.weizhiblog.service.TagService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Log4j2
@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    ArticleService articleService;

    @PostMapping("/article/{aid}")
    public ResponseBean tagAdd(@NotNull @RequestBody TagDTO tagDTO,
                               @NotNull @PathVariable("aid") Integer aid) {
        return tagService.addTag(tagDTO, aid);
    }

    @PostMapping("/article/{aid}/multi")
    public ResponseBean addMultipleTags(@NotNull @RequestBody TagDTO tagDTO,
                                        @PathVariable("aid") Integer aid) {
        return tagService.addTags(tagDTO, aid);
    }

    @DeleteMapping("/{tid}/article/{aid}")
    public ResponseBean deleteTagByAidNidTid(@PathVariable("aid") Integer aid,
                                             @PathVariable("tid") Integer tid) {
        return tagService.deleteTag(tid, aid);
    }

    @DeleteMapping("/article/{aid}")
    public ResponseBean deleteAllTagsByAid(@NotNull @PathVariable Integer aid) {
        return tagService.deleteAllTags(aid);
    }

    @GetMapping("/article/{aid}")
    public ResponseBean listTagsByAid(@NotNull @PathVariable Integer aid) {
        return articleService.listAllTagsByAid(aid);
    }

}
