package com.demo.auth.controller;

import com.demo.auth.entity.Menu;
import com.demo.auth.service.MenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单表(Menu)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@RestController
@RequestMapping("menu")
public class MenuController {
    /**
     * 服务对象
     */
    @Resource
    private MenuService menuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Menu selectOne(Long id) {
        return  menuService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<Menu> findAll() {
        return  menuService.queryAllByLimit(1, -1);
    }

}