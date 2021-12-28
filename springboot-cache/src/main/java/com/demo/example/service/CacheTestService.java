package com.demo.example.service;

import com.demo.example.entity.User;

/**
 * <p> @Title CacheTestService
 * <p> @Description 缓存测试Service
 *
 * @author zhj
 * @date 2021/12/28 15:42
 */
public interface CacheTestService {

    /**
     * 获取用户（以id为key，读写缓存）
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUser(Integer id);

    /**
     * 获取用户（通过spEL表达式匹配方式，读写缓存）
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserBySpEL(Integer id);

    /**
     * 获取用户（通过spEL表达式匹配方式，读写缓存）
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserByKeyGenerator(Integer id);

    /**
     * 获取用户（通过判断是否符合条件，读写缓存）
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserByCondition(Integer id);

    /**
     * 获取用户（通过判断是否不符合条件，读写缓存）
     *
     * @param id 用户ID
     * @return 用户信息
     */
    User getUserByUnless(Integer id);

    /**
     * 添加用户
     *
     * @param user 用户信息
     * @return 用户信息
     */
    User addUser(User user);

    /**
     * 删除用户缓存
     *
     * @param id 用户ID
     */
    void delUserCache(Integer id);
}
