package com.demo.auth.service;

import com.demo.auth.entity.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 菜单表(Menu)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
public interface MenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Menu queryById(Long id);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     */
    PageInfo<Menu> queryByPage(int pageNum, int pageSize);

    /**
     * 查询多条数据
     *
     * @return 对象列表
     */
    List<Menu> queryAll();

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu insert(Menu menu);

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    Menu update(Menu menu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     */
    void deleteById(Long id);

}