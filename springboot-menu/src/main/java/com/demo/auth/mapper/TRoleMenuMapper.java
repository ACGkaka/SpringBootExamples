package com.demo.auth.mapper;

import com.demo.auth.entity.TRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 角色和菜单关联表(TRoleMenu)表数据库访问层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Mapper
public interface TRoleMenuMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param roleId 主键
     * @return 实例对象
     */
    TRoleMenu queryById(Long roleId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TRoleMenu> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tRoleMenu 实例对象
     * @return 对象列表
     */
    List<TRoleMenu> queryAll(TRoleMenu tRoleMenu);

    /**
     * 新增数据
     *
     * @param tRoleMenu 实例对象
     * @return 影响行数
     */
    int insert(TRoleMenu tRoleMenu);

    /**
     * 修改数据
     *
     * @param tRoleMenu 实例对象
     * @return 影响行数
     */
    int update(TRoleMenu tRoleMenu);

    /**
     * 通过主键删除数据
     *
     * @param roleId 主键
     * @return 影响行数
     */
    int deleteById(Long roleId);

}