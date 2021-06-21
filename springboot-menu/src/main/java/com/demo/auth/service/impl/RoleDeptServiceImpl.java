package com.demo.auth.service.impl;

import com.demo.auth.entity.RoleDept;
import com.demo.auth.mapper.RoleDeptMapper;
import com.demo.auth.service.RoleDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色和部门关联表(RoleDept)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service("tRoleDeptService")
public class RoleDeptServiceImpl implements RoleDeptService {
    @Resource
    private RoleDeptMapper roleDeptMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public RoleDept queryById(Long roleId) {
        return  roleDeptMapper.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<RoleDept> queryAllByLimit(int offset, int limit) {
        return  roleDeptMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param roleDept 实例对象
     * @return 实例对象
     */
    @Override
    public RoleDept insert(RoleDept roleDept) {
         roleDeptMapper.insert(roleDept);
        return roleDept;
    }

    /**
     * 修改数据
     *
     * @param roleDept 实例对象
     * @return 实例对象
     */
    @Override
    public RoleDept update(RoleDept roleDept) {
         roleDeptMapper.update(roleDept);
        return  queryById(roleDept.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return  roleDeptMapper.deleteById(roleId) > 0;
    }
}