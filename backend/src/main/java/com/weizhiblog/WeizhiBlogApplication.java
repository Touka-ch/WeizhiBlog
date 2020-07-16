package com.weizhiblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.weizhiblog.mapper")
public class WeizhiBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeizhiBlogApplication.class, args);
    }

}
