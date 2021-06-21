package com.demo.auth.controller;

import com.demo.auth.entity.Menu;
import com.demo.auth.entity.Menu;
import com.demo.auth.service.MenuService;
import com.demo.auth.service.MenuService;
import com.demo.common.result.BaseResult;
import com.github.pagehelper.PageInfo;
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
     * 菜单 - 新增
     */
    @PostMapping("add")
    public BaseResult add(@RequestBody Menu menu) {
        menuService.insert(menu);
        return BaseResult.success().setData(menu);
    }

    /**
     * 菜单 - 通过id查询
     */
    @GetMapping("findById")
    public BaseResult findById(Long id) {
        Menu menu = menuService.queryById(id);
        return BaseResult.success().setData(menu);
    }

    /**
     * 菜单 - 编辑保存
     */
    @PostMapping("save")
    public BaseResult save(@RequestBody Menu menu) {
        menu = menuService.update(menu);
        return BaseResult.success().setData(menu);
    }

    /**
     * 菜单 - 通过id删除
     */
    @DeleteMapping("delete")
    public BaseResult delete(Long id) {
        menuService.deleteById(id);
        return BaseResult.success();
    }

    /**
     * 菜单 - 分页查询
     */
    @GetMapping("findByPage")
    public BaseResult findByPage(int pageNum, int pageSize) {
        PageInfo<Menu> pageInfo = menuService.queryByPage(pageNum, pageSize);
        return BaseResult.success().setData(pageInfo);
    }

}