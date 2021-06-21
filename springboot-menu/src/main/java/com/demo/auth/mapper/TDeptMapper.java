package com.demo.auth.mapper;

import com.demo.auth.entity.TDept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 部门表(TDept)表数据库访问层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Mapper
public interface TDeptMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TDept queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TDept> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param tDept 实例对象
     * @return 对象列表
     */
    List<TDept> queryAll(TDept tDept);

    /**
     * 新增数据
     *
     * @param tDept 实例对象
     * @return 影响行数
     */
    int insert(TDept tDept);

    /**
     * 修改数据
     *
     * @param tDept 实例对象
     * @return 影响行数
     */
    int update(TDept tDept);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}