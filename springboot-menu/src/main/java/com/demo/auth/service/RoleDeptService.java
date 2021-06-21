package com.demo.auth.service;

import com.demo.auth.entity.RoleDept;

import java.util.List;

/**
 * 角色和部门关联表(RoleDept)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
public interface RoleDeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    RoleDept queryById(Long roleId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<RoleDept> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param roleDept 实例对象
     * @return 实例对象
     */
    RoleDept insert(RoleDept roleDept);

    /**
     * 修改数据
     *
     * @param roleDept 实例对象
     * @return 实例对象
     */
    RoleDept update(RoleDept roleDept);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    boolean deleteById(Long roleId);

}