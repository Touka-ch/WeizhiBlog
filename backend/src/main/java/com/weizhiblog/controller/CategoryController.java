package com.weizhiblog.controller;

import com.weizhiblog.bean.Category;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/categories")

public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseBean addCategory(@RequestBody @Validated Category category) {
        return categoryService.addCategory(category);
    }

    @DeleteMapping("/{id}")
    public ResponseBean deleteCategory(@NotNull @PathVariable("id") Integer id) {
        return categoryService.deleteCategory(id);
    }

    @PutMapping("/{id}")
    public ResponseBean putCategory(@PathVariable Integer id,
                                    @RequestBody Category category) {
        return categoryService.putCategory(id,category);
    }

    @PatchMapping("/{id}")
    public ResponseBean patchCategory(@PathVariable Integer id,
                                    @RequestBody Category category) {
        return categoryService.patchCategory(id,category);
    }

    @GetMapping("/user/{uid}")
    public ResponseBean listAllCategoriesByUid(@PathVariable("uid") Integer uid) {
        return categoryService.listAllCategoriesByUid(uid);
    }
}
