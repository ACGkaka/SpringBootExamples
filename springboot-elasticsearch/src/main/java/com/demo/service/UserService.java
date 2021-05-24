package com.demo.service;

import com.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * <p> @Title UserService
 * <p> @Description 用户Service
 *
 * @author zhj
 * @date 2021/5/24 15:26
 */
public interface UserService {

    /**
     * 根据ID查询
     *
     * @param id 主键
     * @return 用户信息
     */
    User findById(String id);

    /**
     * 保存
     *
     * @param user 用户信息
     * @return 用户信息
     */
    User save(User user);

    /**
     * 删除用户
     *
     * @param user 用户信息
     */
    void delete(User user);

    /**
     * 查询全部
     *
     * @return 用户信息
     */
    Iterable<User> findAll();

    /**
     * 根据名称查询
     *
     * @param name 名称
     * @return 用户集合
     */
    List<User> findByName(String name);
}
