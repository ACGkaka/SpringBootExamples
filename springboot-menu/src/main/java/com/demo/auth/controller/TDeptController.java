package com.demo.auth.controller;

import com.demo.auth.entity.TDept;
import com.demo.auth.service.TDeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门表(TDept)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@RestController
@RequestMapping("tDept")
public class TDeptController {
    /**
     * 服务对象
     */
    @Resource
    private TDeptService tDeptService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TDept selectOne(Long id) {
        return this.tDeptService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<TDept> findAll() {
        return this.tDeptService.queryAllByLimit(1, -1);
    }

}