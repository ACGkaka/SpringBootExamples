package com.demo.auth.service.impl;

import com.demo.auth.entity.Dept;
import com.demo.auth.mapper.DeptMapper;
import com.demo.auth.service.DeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门表(Dept)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Service("tDeptService")
public class DeptServiceImpl implements DeptService {
    @Resource
    private DeptMapper deptMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Dept queryById(Long id) {
        return  deptMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Dept> queryAllByLimit(int offset, int limit) {
        return  deptMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public Dept insert(Dept dept) {
         deptMapper.insert(dept);
        return dept;
    }

    /**
     * 修改数据
     *
     * @param dept 实例对象
     * @return 实例对象
     */
    @Override
    public Dept update(Dept dept) {
         deptMapper.update(dept);
        return  queryById(dept.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return  deptMapper.deleteById(id) > 0;
    }
}