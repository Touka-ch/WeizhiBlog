package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.ArticleService;
import com.weizhiblog.service.TagService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    ArticleService articleService;

    @PostMapping("/article/{aid}")
    public ResponseBean tagAdd(@NotNull @RequestParam("tag") String tag,
                               @NotNull @PathVariable("aid") Integer aid) {
        return tagService.addTag(tag, aid);
    }

    @PostMapping("/article/{aid}/multi")
    public ResponseBean addMultipleTags(@RequestParam("tags") List<String> tags,
                                        @PathVariable("aid") Integer aid) {
        return tagService.addTags(tags, aid);
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
