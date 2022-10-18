package com.demo;

import com.demo.module.entity.TUser;
import com.demo.module.service.TUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class SpringbootDemoApplicationTests {

    @Autowired
    private TUserService userService;

    @Test
    void saveTest() {
        List<TUser> users = new ArrayList<>(3);
        users.add(new TUser("ACGkaka_1", "123456", 10));
        users.add(new TUser("ACGkaka_2", "123456", 11));
        users.add(new TUser("ACGkaka_3", "123456", 12));
        userService.saveBatch(users);
    }

    @Test
    void listTest() {
        List<TUser> users = userService.list();
        System.out.println(">>>>>>>>>> 【Result】<<<<<<<<<< ");
        users.forEach(System.out::println);
    }

}
