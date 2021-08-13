package com.demo.module.service;

import com.demo.module.entity.TUser;
import java.util.List;

/**
 * 用户表(TUser)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 16:49:55
 */
public interface TUserService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TUser queryById(Long id);

    /**
     * 查询全部数据
     *
     * @return 对象列表
     */
    List<TUser> queryAll();

    /**
     * 新增数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser insert(TUser tUser);

    /**
     * 修改数据
     *
     * @param tUser 实例对象
     * @return 实例对象
     */
    TUser update(TUser tUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}