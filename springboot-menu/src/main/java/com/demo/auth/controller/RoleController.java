package com.demo.auth.controller;

import com.demo.auth.entity.Role;
import com.demo.auth.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(Role)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@RestController
@RequestMapping("role")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Role selectOne(Long id) {
        return  roleService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<Role> findAll() {
        return  roleService.queryAllByLimit(1, -1);
    }

}