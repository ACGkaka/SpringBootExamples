package com.demo.auth.controller;

import com.demo.auth.entity.TMenu;
import com.demo.auth.service.TMenuService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜单表(TMenu)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@RestController
@RequestMapping("tMenu")
public class TMenuController {
    /**
     * 服务对象
     */
    @Resource
    private TMenuService tMenuService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TMenu selectOne(Long id) {
        return this.tMenuService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<TMenu> findAll() {
        return this.tMenuService.queryAllByLimit(1, -1);
    }

}