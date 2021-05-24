package com.demo;

import com.alibaba.fastjson.JSON;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;

import java.awt.print.Book;

/**
 * <p> @Title ElasticsearchTests
 * <p> @Description Elasticsearch 测试类
 *
 * @author zhj
 * @date 2021/5/24 15:37
 */
@SpringBootTest
public class ElasticsearchTests {

    @Autowired
    private UserService userService;

    @Test
    void saveTest() {
        // ID会作为主键生效
        User user = new User("111", "kaka123", 24);
        User save = userService.save(user);
        System.out.println(JSON.toJSONString(user));
        System.out.println(JSON.toJSONString(save));
    }

    @Test
    void findByIdTest() {
        User user = userService.findById("111");
        System.out.println(JSON.toJSONString(user));
    }
}
