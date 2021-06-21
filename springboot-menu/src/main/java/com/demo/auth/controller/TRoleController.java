package com.demo.auth.controller;

import com.demo.auth.entity.TRole;
import com.demo.auth.service.TRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(TRole)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@RestController
@RequestMapping("tRole")
public class TRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleService tRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TRole selectOne(Long id) {
        return this.tRoleService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<TRole> findAll() {
        return this.tRoleService.queryAllByLimit(1, -1);
    }

}