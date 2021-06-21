package com.demo.auth.service.impl;

import com.demo.auth.entity.Menu;
import com.demo.auth.mapper.MenuMapper;
import com.demo.auth.service.MenuService;
import com.demo.common.exception.BaseException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单表(Menu)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Resource
    private MenuMapper menuMapper;

    @Override
    public Menu queryById(Long id) {
        // 根据ID查找
        return menuMapper.queryById(id);
    }

    @Override
    public PageInfo<Menu> queryByPage(int pageNum, int pageSize) {
        // 分页查找
        PageHelper.startPage(pageNum, pageSize);
        List<Menu> list = menuMapper.queryAll();
        return new PageInfo<>(list);
    }

    @Override
    public List<Menu> queryAll() {
        // 查询全部
        return menuMapper.queryAll();
    }

    @Override
    public Menu insert(Menu menu) {
        // 新增菜单
        if (menu.getParentId() == null) {
            menu.setParentId(-1L);
        }
        menu.setCreateTime(LocalDateTime.now());
        menu.setUpdateTime(LocalDateTime.now());
        menuMapper.insert(menu);
        return menu;
    }

    @Override
    public Menu update(Menu menu) {
        // 编辑菜单
        Menu result = menuMapper.queryById(menu.getId());
        if (result == null) {
            throw new BaseException("菜单不存在");
        }
        menu.setUpdateTime(LocalDateTime.now());
        menuMapper.update(menu);
        return  queryById(menu.getId());
    }

    @Override
    public void deleteById(Long id) {
        // 删除菜单
        Menu menu = menuMapper.queryById(id);
        if (menu == null) {
            throw new BaseException("菜单不存在");
        }
        menuMapper.deleteById(id);
    }
}