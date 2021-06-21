package com.demo.auth.service.impl;

import com.demo.auth.entity.TRole;
import com.demo.auth.mapper.TRoleMapper;
import com.demo.auth.service.TRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(TRole)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service("tRoleService")
public class TRoleServiceImpl implements TRoleService {
    @Resource
    private TRoleMapper tRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TRole queryById(Long id) {
        return this.tRoleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TRole> queryAllByLimit(int offset, int limit) {
        return this.tRoleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    @Override
    public TRole insert(TRole tRole) {
        this.tRoleMapper.insert(tRole);
        return tRole;
    }

    /**
     * 修改数据
     *
     * @param tRole 实例对象
     * @return 实例对象
     */
    @Override
    public TRole update(TRole tRole) {
        this.tRoleMapper.update(tRole);
        return this.queryById(tRole.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.tRoleMapper.deleteById(id) > 0;
    }
}