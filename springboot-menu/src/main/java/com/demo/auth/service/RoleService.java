package com.demo.auth.service;

import com.demo.auth.entity.Role;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 角色表(Role)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
public interface RoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Role queryById(Long id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     */
    PageInfo<Role> queryByPage(int pageNum, int pageSize);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Role> queryAll();

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role insert(Role role);

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    Role update(Role role);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    void deleteById(Long id);

}