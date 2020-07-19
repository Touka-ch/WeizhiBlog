package com.weizhiblog.mapper;

import com.weizhiblog.bean.Article;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
 *
 * @createTime 07-16 19:21:20
 * @author Touka_
 * @classname com.weizhiblog.mapper.ArticleMapperTest
 * @lastModifiedTime 7月16日   19:21:20
 */
@SpringBootTest
class ArticleMapperTest {
    @Autowired
    ArticleMapper articleMapper;

    @Test
    void deleteByPrimaryKey() {
        System.out.println(articleMapper.deleteByPrimaryKey(2));
    }

    @Test
    void insert() {
        System.out.println(articleMapper.insert(new Article()));
    }

    @Test
    void insertSelective() {
    }

    @Test
    void selectByPrimaryKey() {
    }

    @Test
    void updateByPrimaryKeySelective() {
    }

    @Test
    void updateByPrimaryKey() {
    }
}