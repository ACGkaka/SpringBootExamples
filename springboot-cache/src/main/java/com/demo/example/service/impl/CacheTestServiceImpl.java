package com.demo.example.service.impl;

import com.demo.example.entity.User;
import com.demo.example.service.CacheTestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p> @Title CacheTestServiceImpl
 * <p> @Description 缓存测试ServiceImpl
 *
 * @author zhj
 * @date 2021/12/28 15:43
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "users")
public class CacheTestServiceImpl implements CacheTestService {

    /**
     * 模拟数据库
     */
    private static Map<Integer, User> map = new HashMap<>();

    static {
        // 初始化数据库
        map.put(1, new User(1, "Tom", 18, "北京"));
        map.put(2, new User(2, "Jack", 19, "辽宁"));
        map.put(3, new User(3, "Lily", 17, "海南"));
    }


    @Override
    @Cacheable(cacheNames = "users", key="#id")
    public User getUser(Integer id) {
        // 获取用户
        log.info(">>>>>>>>>> 【获取用户】getUser() id: {}", id);
        return map.get(id);
    }

    /**
     * 这里的 key，我想用 getUserBySpEL[2] 这样的形式，可以通过拼接实现，这里使用了 spEL 表达式语法
     */
    @Override
    @Cacheable(cacheNames = "usersBySpEL", key="#root.methodName + '[' + #id + ']'")
    public User getUserBySpEL(Integer id) {
        // 获取用户
        log.info(">>>>>>>>>> 【获取用户】getUserBySpEL() id: {}", id);
        return map.get(id);
    }

    /**
     * 这里的 keyGenerator 写我们自定义的 keyGenerator
     * key 和 keyGenerator 二选一
     */
    @Override
    @Cacheable(cacheNames = "userByKeyGenerator", keyGenerator = "myKeyGenerator")
    public User getUserByKeyGenerator(Integer id) {
        // 获取用户
        log.info(">>>>>>>>>> 【获取用户】getUserByKeyGenerator() id: {}", id);
        return map.get(id);
    }

    /**
     * id 的值大于 1 才进行缓存（注意：字符串不能判断大小，值为null时可正常判断）
     */
    @Override
    @Cacheable(cacheNames = "userByCondition", condition = "#id > 1")
    public User getUserByCondition(Integer id) {
        // 获取用户
        log.info(">>>>>>>>>> 【获取用户】getUserByCondition() id: {}", id);
        return map.get(id);
    }

    /**
     * id 的值大于 1 不进行缓存（注意：字符串不能判断大小，值为null时可正常判断）
     */
    @Override
    @Cacheable(cacheNames = "userByUnless", unless = "#id > 1")
    public User getUserByUnless(Integer id) {
        // 获取用户
        log.info(">>>>>>>>>> 【获取用户】getUserByUnless() id: {}", id);
        return map.get(id);
    }

    @Override
    @Caching(put = {
            @CachePut(cacheNames = "users" , key = "#user.id"),
            @CachePut(cacheNames = "users" , key = "#user.username"),
    })
    public User addUser(User user) {
        // 添加用户
        log.info(">>>>>>>>>> 【添加用户】addUser: {}", user);
        map.put(user.getId(), user);
        return user;
    }

    @Override
    @CacheEvict(cacheNames = "users", key = "#id")
    public void delUserCache(Integer id) {
        // 删除用户缓存
        log.info(">>>>>>>>>> 【删除用户缓存】 delUserCache id: {}", id);
    }
}
