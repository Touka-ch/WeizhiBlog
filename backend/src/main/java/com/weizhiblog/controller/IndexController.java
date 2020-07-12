package com.weizhiblog.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping("/*")
    public String getIndex(){
        return "这是主页";
    }

}
