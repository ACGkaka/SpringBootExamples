package com.demo.auth.mapper;

import com.demo.auth.entity.TUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 用户和角色关联表(TUserRole)表数据库访问层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Mapper
public interface TUserRoleMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param userId 主键
     * @return 实例对象
     */
    TUserRole queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TUserRole> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tUserRole 实例对象
     * @return 对象列表
     */
    List<TUserRole> queryAll(TUserRole tUserRole);

    /**
     * 新增数据
     *
     * @param tUserRole 实例对象
     * @return 影响行数
     */
    int insert(TUserRole tUserRole);

    /**
     * 修改数据
     *
     * @param tUserRole 实例对象
     * @return 影响行数
     */
    int update(TUserRole tUserRole);

    /**
     * 通过主键删除数据
     *
     * @param userId 主键
     * @return 影响行数
     */
    int deleteById(Long userId);

}