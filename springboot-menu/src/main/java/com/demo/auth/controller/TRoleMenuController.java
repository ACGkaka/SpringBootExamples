package com.demo.auth.controller;

import com.demo.auth.entity.TRoleMenu;
import com.demo.auth.service.TRoleMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色和菜单关联表(TRoleMenu)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@RestController
@RequestMapping("tRoleMenu")
public class TRoleMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleMenuService tRoleMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TRoleMenu selectOne(Long id) {
        return this.tRoleMenuService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<TRoleMenu> findAll() {
        return this.tRoleMenuService.queryAllByLimit(1, -1);
    }

}