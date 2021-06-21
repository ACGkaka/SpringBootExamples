package com.demo.auth.service.impl;

import com.demo.auth.entity.TRoleMenu;
import com.demo.auth.mapper.TRoleMenuMapper;
import com.demo.auth.service.TRoleMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色和菜单关联表(TRoleMenu)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service("tRoleMenuService")
public class TRoleMenuServiceImpl implements TRoleMenuService {
    @Resource
    private TRoleMenuMapper tRoleMenuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    @Override
    public TRoleMenu queryById(Long roleId) {
        return this.tRoleMenuMapper.queryById(roleId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TRoleMenu> queryAllByLimit(int offset, int limit) {
        return this.tRoleMenuMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TRoleMenu insert(TRoleMenu tRoleMenu) {
        this.tRoleMenuMapper.insert(tRoleMenu);
        return tRoleMenu;
    }

    /**
     * 修改数据
     *
     * @param tRoleMenu 实例对象
     * @return 实例对象
     */
    @Override
    public TRoleMenu update(TRoleMenu tRoleMenu) {
        this.tRoleMenuMapper.update(tRoleMenu);
        return this.queryById(tRoleMenu.getRoleId());
    }

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long roleId) {
        return this.tRoleMenuMapper.deleteById(roleId) > 0;
    }
}