package com.demo.auth.service.impl;

import com.demo.auth.entity.TDept;
import com.demo.auth.mapper.TDeptMapper;
import com.demo.auth.service.TDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门表(TDept)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Service("tDeptService")
public class TDeptServiceImpl implements TDeptService {
    @Resource
    private TDeptMapper tDeptMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TDept queryById(Long id) {
        return this.tDeptMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TDept> queryAllByLimit(int offset, int limit) {
        return this.tDeptMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tDept 实例对象
     * @return 实例对象
     */
    @Override
    public TDept insert(TDept tDept) {
        this.tDeptMapper.insert(tDept);
        return tDept;
    }

    /**
     * 修改数据
     *
     * @param tDept 实例对象
     * @return 实例对象
     */
    @Override
    public TDept update(TDept tDept) {
        this.tDeptMapper.update(tDept);
        return this.queryById(tDept.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tDeptMapper.deleteById(id) > 0;
    }
}