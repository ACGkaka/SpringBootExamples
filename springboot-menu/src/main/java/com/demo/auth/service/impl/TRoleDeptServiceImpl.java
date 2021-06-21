package com.demo.auth.service.impl;

import com.demo.auth.entity.TRoleDept;
import com.demo.auth.mapper.TRoleDeptMapper;
import com.demo.auth.service.TRoleDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色和部门关联表(TRoleDept)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service("tRoleDeptService")
public class TRoleDeptServiceImpl implements TRoleDeptService {
    @Resource
    private TRoleDeptMapper tRoleDeptMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public TRoleDept queryById(Long roleId) {
        return this.tRoleDeptMapper.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TRoleDept> queryAllByLimit(int offset, int limit) {
        return this.tRoleDeptMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRoleDept 实例对象
     * @return 实例对象
     */
    @Override
    public TRoleDept insert(TRoleDept tRoleDept) {
        this.tRoleDeptMapper.insert(tRoleDept);
        return tRoleDept;
    }

    /**
     * 修改数据
     *
     * @param tRoleDept 实例对象
     * @return 实例对象
     */
    @Override
    public TRoleDept update(TRoleDept tRoleDept) {
        this.tRoleDeptMapper.update(tRoleDept);
        return this.queryById(tRoleDept.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.tRoleDeptMapper.deleteById(roleId) > 0;
    }
}