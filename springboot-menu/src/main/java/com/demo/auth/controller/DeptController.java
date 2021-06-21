package com.demo.auth.controller;

import com.demo.auth.entity.Dept;
import com.demo.auth.entity.Dept;
import com.demo.auth.service.DeptService;
import com.demo.common.result.BaseResult;
import com.github.pagehelper.PageInfo;
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
     * 部门 - 新增
     */
    @PostMapping("add")
    public BaseResult add(@RequestBody Dept dept) {
        deptService.insert(dept);
        return BaseResult.success().setData(dept);
    }

    /**
     * 部门 - 通过id查询
     */
    @GetMapping("findById")
    public BaseResult findById(Long id) {
        Dept dept = deptService.queryById(id);
        return BaseResult.success().setData(dept);
    }

    /**
     * 部门 - 编辑保存
     */
    @PostMapping("save")
    public BaseResult save(@RequestBody Dept dept) {
        dept = deptService.update(dept);
        return BaseResult.success().setData(dept);
    }

    /**
     * 部门 - 通过id删除
     */
    @DeleteMapping("delete")
    public BaseResult delete(Long id) {
        deptService.deleteById(id);
        return BaseResult.success();
    }

    /**
     * 部门 - 分页查询
     */
    @GetMapping("findByPage")
    public BaseResult findByPage(int pageNum, int pageSize) {
        PageInfo<Dept> pageInfo = deptService.queryByPage(pageNum, pageSize);
        return BaseResult.success().setData(pageInfo);
    }

}