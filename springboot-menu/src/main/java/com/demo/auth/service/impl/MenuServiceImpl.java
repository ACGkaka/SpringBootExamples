package com.demo.auth.service.impl;

import com.demo.auth.entity.Menu;
import com.demo.auth.mapper.MenuMapper;
import com.demo.auth.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Service("tMenuService")
public class MenuServiceImpl implements MenuService {
    @Resource
    private MenuMapper menuMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Menu queryById(Long id) {
        return  menuMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<Menu> queryAllByLimit(int offset, int limit) {
        return  menuMapper.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu insert(Menu menu) {
         menuMapper.insert(menu);
        return menu;
    }

    /**
     * 修改数据
     *
     * @param menu 实例对象
     * @return 实例对象
     */
    @Override
    public Menu update(Menu menu) {
         menuMapper.update(menu);
        return  queryById(menu.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return  menuMapper.deleteById(id) > 0;
    }
}