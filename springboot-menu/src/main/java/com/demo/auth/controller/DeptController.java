package com.demo.auth.controller;

import com.demo.auth.entity.Dept;
import com.demo.auth.service.DeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 部门表(Dept)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:03
 */
@RestController
@RequestMapping("dept")
public class DeptController {
    /**
     * 服务对象
     */
    @Resource
    private DeptService deptService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public Dept selectOne(Long id) {
        return  deptService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<Dept> findAll() {
        return  deptService.queryAllByLimit(1, -1);
    }

}