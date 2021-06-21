package com.demo.auth.controller;

import com.demo.auth.entity.TRoleDept;
import com.demo.auth.service.TRoleDeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色和部门关联表(TRoleDept)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@RestController
@RequestMapping("tRoleDept")
public class TRoleDeptController {
    /**
     * 服务对象
     */
    @Resource
    private TRoleDeptService tRoleDeptService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TRoleDept selectOne(Long id) {
        return this.tRoleDeptService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<TRoleDept> findAll() {
        return this.tRoleDeptService.queryAllByLimit(1, -1);
    }

}