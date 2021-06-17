package com.demo;

import com.demo.entity.UserEntity;
import com.demo.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1() {
        // 测试字符串，保存、查询
        String key = "redis_test:strKey";
        redisUtil.set(key, "acgkaka");
        // true
        System.out.println("hasKey: " + redisUtil.hasKey(key));
        // []
        System.out.println("keys1: " + redisUtil.keys("redis"));
        // [redis_test:strKey]
        System.out.println("keys2: " + redisUtil.keys("redis*"));
        // -1
        System.out.println("getExpire: " + redisUtil.getExpire(key));
        // false
        System.out.println("isExpire: " + redisUtil.isExpire(key));
        // acgkaka
        System.out.println("value: " + redisUtil.get(key));
    }

    @Test
    public void test2() {
        // 保存对象
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName("清风烟柳");
        user.setUserSex("男");
        redisUtil.set("redis_test:user", user);
        // 查询对象
        UserEntity user2 = (UserEntity) redisUtil.get("redis_test:user");
        System.out.println("user:" + user2.getId() + "," + user2.getUserName() + "," + user2.getUserSex());
    }

    @Test
    public void test3() {
        // 测试前缀
        String key = "strKey";
        redisUtil.setWithPrefix(key, "acgkaka");
        // false
        System.out.println("hasKey: " + redisUtil.hasKey(key));
        // true
        System.out.println("hasKey: " + redisUtil.hasKeyWithPrefix(key));
        // [redis_test:strKey, redis_test:user, ACGkaka:strKey]
        System.out.println("keys: " + redisUtil.keys("*"));
        // null
        System.out.println("value: " + redisUtil.get(key));
        // acgkaka
        System.out.println("value: " + redisUtil.getWithPrefix(key));
    }

}
