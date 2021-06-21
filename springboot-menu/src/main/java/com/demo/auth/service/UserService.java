package com.demo.auth.service;

import com.demo.auth.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 用户表(User)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    User queryById(Long id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     */
    PageInfo<User> queryByPage(int pageNum, int pageSize);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<User> queryAll();

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    void deleteById(Long id);

}