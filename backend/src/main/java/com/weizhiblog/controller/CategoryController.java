package com.weizhiblog.controller;

import com.weizhiblog.bean.Category;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.CategoryService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Log4j2
@RestController
@RequestMapping("/category")

public class CategoryController {
    @Autowired
    CategoryService categoryService;

    /**
     * 新建目录
     *
     * @param category 目录信息
     * @return 是否新建成功
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    public ResponseBean categoryAdd(@RequestBody @Validated Category category) {
        return categoryService.insertCategory(category);
    }

    /**
     * 删除指定目录
     *
     * @param id 删除目录的id
     * @return 是否删除成功
     */
    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseBean categoryDelete(@NotNull Integer id) {
        return categoryService.deleteCategory(id);
    }

    /**
     * 修改目录名称
     *
     * @param category 目录信息
     * @return 是否修改成功
     */
    @RequestMapping(value = "/update", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean categoryUpdate(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    /**
     * 查找用户在指定目录下的所有文章
     *
     * @param uid 用户id
     * @param cid 目录id
     * @return
     */
    @RequestMapping(value = "/get", method = {RequestMethod.POST, RequestMethod.DELETE})
    public ResponseBean articleGetByCategory(@RequestParam("uid") @NotNull Integer uid,
                                             @RequestParam("cid") @NotNull Integer cid) {
        return categoryService.getArticleByCategory(uid, cid);
    }


}
