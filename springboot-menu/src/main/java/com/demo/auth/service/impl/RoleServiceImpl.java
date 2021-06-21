package com.demo.auth.service.impl;

import com.demo.auth.entity.Role;
import com.demo.auth.entity.Role;
import com.demo.auth.mapper.RoleMapper;
import com.demo.auth.service.RoleService;
import com.demo.common.exception.BaseException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 角色表(Role)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public Role queryById(Long id) {
        // 根据ID查找
        return roleMapper.queryById(id);
    }

    @Override
    public PageInfo<Role> queryByPage(int pageNum, int pageSize) {
        // 分页查找
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.queryAll();
        return new PageInfo<>(list);
    }

    @Override
    public List<Role> queryAll() {
        // 查询全部
        return roleMapper.queryAll();
    }

    @Override
    public Role insert(Role role) {
        // 新增角色
        Role query = new Role();
        query.setRoleName(role.getRoleName());
        List<Role> list = roleMapper.query(query);
        if (list != null && list.size() > 0) {
            throw new BaseException("角色名称已存在");
        }
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.insert(role);
        return role;
    }

    @Override
    public Role update(Role role) {
        // 编辑角色
        Role result = roleMapper.queryById(role.getId());
        if (result == null) {
            throw new BaseException("角色不存在");
        }
        role.setUpdateTime(LocalDateTime.now());
        roleMapper.update(role);
        return  queryById(role.getId());
    }

    @Override
    public void deleteById(Long id) {
        // 删除角色
        Role role = roleMapper.queryById(id);
        if (role == null) {
            throw new BaseException("角色不存在");
        }
        roleMapper.deleteById(id);
    }
}