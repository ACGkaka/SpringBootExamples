package com.demo.auth.service;

import com.demo.auth.entity.TUserRole;
import java.util.List;

/**
 * 用户和角色关联表(TUserRole)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
public interface TUserRoleService {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TUserRole queryById(Long userId);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUserRole> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    TUserRole insert(TUserRole tUserRole);

    /**
     * 修改数据
     *
     * @param tUserRole 实例对象
     * @return 实例对象
     */
    TUserRole update(TUserRole tUserRole);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 是否成功
     */
    boolean deleteById(Long userId);

}