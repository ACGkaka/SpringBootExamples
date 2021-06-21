package com.demo.auth.service;

import com.demo.auth.entity.TMenu;
import java.util.List;

/**
 * 菜单表(TMenu)表服务接口
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
public interface TMenuService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TMenu queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TMenu> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    TMenu insert(TMenu tMenu);

    /**
     * 修改数据
     *
     * @param tMenu 实例对象
     * @return 实例对象
     */
    TMenu update(TMenu tMenu);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}