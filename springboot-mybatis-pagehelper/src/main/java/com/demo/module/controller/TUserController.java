package com.demo.module.controller;

import com.demo.module.entity.TUser;
import com.demo.module.page.PageParam;
import com.demo.module.page.PageResult;
import com.demo.module.page.PageStart;
import com.demo.module.service.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(TUser)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 16:49:55
 */
@RestController
@RequestMapping("tUser")
public class TUserController {
    /**
     * 服务对象
     */
    @Resource
    private TUserService tUserService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TUser selectOne(Long id) {
        return tUserService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<TUser> findAll(Integer pageNum, Integer pageSize) {
        return tUserService.queryAll();
    }

    /**
     * 分页查询（简单分页）
     * 参数：pageNum:页码 pageSize:每页条数
     *
     * @return 全部数据
     */
    @GetMapping("page")
    public PageResult<TUser> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<TUser> pageInfo = new PageInfo<>(tUserService.queryAll());
        return new PageResult<>(pageInfo);
    }

    /**
     * 查询全部数据（分页 + 排序，只支持GET请求 + POST请求form传参）
     * 参数：pageNum:页码 pageSize:每页条数
     *
     * @return 全部数据
     */
    @GetMapping("page2")
    public PageResult<TUser> page2() {
        PageStart.startPage();
        PageInfo<TUser> pageInfo = new PageInfo<>(tUserService.queryAll());
        return new PageResult<>(pageInfo);
    }

    /**
     * 查询全部数据（分页 + 排序，只支持POST请求json传参）
     * 参数：pageNum:页码 pageSize:每页条数
     *
     * @return 全部数据
     */
    @PostMapping("page3")
    public PageResult<TUser> page3(@RequestBody PageParam pageParam) {
        PageStart.startPage(pageParam);
        PageInfo<TUser> pageInfo = new PageInfo<>(tUserService.queryAll());
        return new PageResult<>(pageInfo);
    }

}