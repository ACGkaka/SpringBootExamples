package com.demo.auth.service;

import com.demo.auth.entity.TDept;
import java.util.List;

/**
 * 部门表(TDept)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
public interface TDeptService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TDept queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDept> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tDept 实例对象
     * @return 实例对象
     */
    TDept insert(TDept tDept);

    /**
     * 修改数据
     *
     * @param tDept 实例对象
     * @return 实例对象
     */
    TDept update(TDept tDept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}