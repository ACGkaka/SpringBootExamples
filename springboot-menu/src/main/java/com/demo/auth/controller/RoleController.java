package com.demo.auth.controller;

import com.demo.auth.entity.Role;
import com.demo.auth.service.RoleService;
import com.demo.common.result.BaseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
     * 角色 - 新增
     */
    @PostMapping("add")
    public BaseResult add(@RequestBody Role role) {
        roleService.insert(role);
        return BaseResult.success().setData(role);
    }

    /**
     * 角色 - 通过id查询
     */
    @GetMapping("findById")
    public BaseResult findById(Long id) {
        Role role = roleService.queryById(id);
        return BaseResult.success().setData(role);
    }

    /**
     * 角色 - 编辑保存
     */
    @PostMapping("save")
    public BaseResult save(@RequestBody Role role) {
        role = roleService.update(role);
        return BaseResult.success().setData(role);
    }

    /**
     * 角色 - 通过id删除
     */
    @DeleteMapping("delete")
    public BaseResult delete(Long id) {
        roleService.deleteById(id);
        return BaseResult.success();
    }

    /**
     * 角色 - 分页查询
     */
    @GetMapping("findByPage")
    public BaseResult findByPage(int pageNum, int pageSize) {
        PageInfo<Role> pageInfo = roleService.queryByPage(pageNum, pageSize);
        return BaseResult.success().setData(pageInfo);
    }

}