package com.demo.auth.service.impl;

import com.demo.auth.entity.Role;
import com.demo.auth.mapper.RoleMapper;
import com.demo.auth.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(Role)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service("tRoleService")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Role queryById(Long id) {
        return  roleMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Role> queryAllByLimit(int offset, int limit) {
        return  roleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role insert(Role role) {
         roleMapper.insert(role);
        return role;
    }

    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 实例对象
     */
    @Override
    public Role update(Role role) {
         roleMapper.update(role);
        return  queryById(role.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return  roleMapper.deleteById(id) > 0;
    }
}