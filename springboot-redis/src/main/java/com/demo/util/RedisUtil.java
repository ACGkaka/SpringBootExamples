package com.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p> @Title RedisUtil
 * <p> @Description Redis工具类
 *
 * @author ACGkaka
 * @date 2021/6/16 16:32
 */
@Component
public class RedisUtil {

    @Qualifier("redisTemplate")
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Qualifier("prefixRedisTemplate")
    @Resource
    private RedisTemplate<String, Object> prefixRedisTemplate;

    /**
     * 放入缓存
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public boolean set(String key, Object value) {
        try {
            redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 放入缓存
     *
     * @param key   键，带前缀
     * @param value 值
     * @return true成功 false失败
     */
    public boolean setWithPrefix(String key, Object value) {
        try {
            prefixRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取缓存
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取缓存
     *
     * @param key 键，带前缀
     * @return 值
     */
    public Object getWithPrefix(String key) {
        return key == null ? null : prefixRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取keys
     *
     * @param keyPattern 模糊查找key，例：user*
     * @return 匹配结果，例：user:name
     */
    public Set<String> keys(String keyPattern) {
        try {
            return redisTemplate.keys(keyPattern);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key      键
     * @param value    值
     * @param time     时间
     * @param timeUnit 时间单位，例：TimeUnit.SECONDS
     * @return true成功 false 失败
     */
    public boolean set(String key, Object value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                redisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 指定缓存失效时间
     *
     * @param key      键
     * @param timeUnit 时间单位，例：TimeUnit.SECONDS
     * @return true成功 false失败
     */
    public boolean expire(String key, long time, TimeUnit timeUnit) {
        return Boolean.TRUE.equals(this.redisTemplate.expire(key, time, timeUnit));
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效，-1没有设置过期时间，-2 key不存在
     */
    public long getExpire(String key) {
        //此方法返回单位为秒过期时长
        Long seconds = redisTemplate.opsForValue().getOperations().getExpire(key);
        if (seconds == null) {
            return -1L;
        } else {
            return seconds;
        }
    }

    /**
     * 根据key 判断是否过期
     *
     * @param key 键 不能为null
     * @return true过期 false没过期
     */
    public boolean isExpire(String key) {
        long seconds = getExpire(key);
        return seconds > 0;
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKey(String key) {
        if (key == null) {
            return false;
        }
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public boolean hasKeyWithPrefix(String key) {
        if (key == null) {
            return false;
        }
        return Boolean.TRUE.equals(prefixRedisTemplate.hasKey(key));
    }

}
