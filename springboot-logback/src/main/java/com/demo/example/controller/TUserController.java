package com.demo.example.controller;

import com.demo.example.entity.TUser;
import com.demo.example.service.TUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return this.tUserService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<TUser> findAll() {
        return this.tUserService.queryAllByLimit(1, -1);
    }

}