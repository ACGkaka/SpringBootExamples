package com.demo;

import com.demo.entity.UserEntity;
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
    private RedisTemplate<String, String> strRedisTemplate;
    @Autowired
    private RedisTemplate<String, Serializable> serializableRedisTemplate;

    @Test
    public void testString1() {
        strRedisTemplate.opsForValue().set("redis_test:strKey", "acgkaka");
        System.out.println(strRedisTemplate.opsForValue().get("redis_test:strKey"));
    }

    @Test
    public void testString2() {
        strRedisTemplate.boundValueOps("redis_test:strKey222").set("acgkaka222");
        System.out.println(strRedisTemplate.boundValueOps("redis_test:strKey222").get());
    }

    @Test
    public void testSerializable() {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setUserName("清风烟柳");
        user.setUserSex("男");
        serializableRedisTemplate.opsForValue().set("redis_test:user", user);
        UserEntity user2 = (UserEntity) serializableRedisTemplate.opsForValue().get("redis_test:user");
        System.out.println("user:" + user2.getId() + "," + user2.getUserName() + "," + user2.getUserSex());
    }

}
