package com.demo.auth.service.impl;

import com.demo.auth.entity.TUserRole;
import com.demo.auth.mapper.TUserRoleMapper;
import com.demo.auth.service.TUserRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户和角色关联表(TUserRole)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service("tUserRoleService")
public class TUserRoleServiceImpl implements TUserRoleService {
    @Resource
    private TUserRoleMapper tUserRoleMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    @Override
    public TUserRole queryById(Long userId) {
        return this.tUserRoleMapper.queryById(userId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TUserRole> queryAllByLimit(int offset, int limit) {
        return this.tUserRoleMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public TUserRole insert(TUserRole tUserRole) {
        this.tUserRoleMapper.insert(tUserRole);
        return tUserRole;
    }

    /**
     * 修改数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    @Override
    public TUserRole update(TUserRole tUserRole) {
        this.tUserRoleMapper.update(tUserRole);
        return this.queryById(tUserRole.getUserId());
    }

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long userId) {
        return this.tUserRoleMapper.deleteById(userId) > 0;
    }
}