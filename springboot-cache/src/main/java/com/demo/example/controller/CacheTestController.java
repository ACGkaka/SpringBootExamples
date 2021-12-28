package com.demo.example.controller;

import com.demo.example.entity.User;
import com.demo.example.service.CacheTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> @Title CacheTestController
 * <p> @Description 测试缓存
 *
 * @author zhj
 * @date 2021/12/28 15:40
 */
@RestController
@RequestMapping("/cache")
public class CacheTestController {

    @Autowired
    private CacheTestService cacheTestService;

    @GetMapping("/getUser")
    public User getUser(Integer id) {
        return cacheTestService.getUser(id);
    }

    @GetMapping("/getUserBySpEL")
    public User getUserBySpEL(Integer id) {
        return cacheTestService.getUserBySpEL(id);
    }

    @GetMapping("/getUserByKeyGenerator")
    public User getUserByKeyGenerator(Integer id) {
        return cacheTestService.getUserByKeyGenerator(id);
    }

    @GetMapping("/getUserByCondition")
    public User getUserByCondition(Integer id) {
        return cacheTestService.getUserByCondition(id);
    }

    @GetMapping("/getUserByUnless")
    public User getUserByUnless(Integer id) {
        return cacheTestService.getUserByUnless(id);
    }

    @GetMapping("/addUser")
    public User addUser(Integer id, String username, Integer age, String city) {
        User user = new User(id, username, age, city);
        return cacheTestService.addUser(user);
    }

    @GetMapping("/delUserCache")
    public String delUserCache(Integer id) {
        cacheTestService.delUserCache(id);
        return "Operate succeed";
    }
}
