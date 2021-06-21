package com.demo.auth.service;

import com.demo.auth.entity.Dept;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 部门表(Dept)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
public interface DeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Dept queryById(Long id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     */
    PageInfo<Dept> queryByPage(int pageNum, int pageSize);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Dept> queryAll();

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    Dept insert(Dept dept);

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    Dept update(Dept dept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    void deleteById(Long id);

}