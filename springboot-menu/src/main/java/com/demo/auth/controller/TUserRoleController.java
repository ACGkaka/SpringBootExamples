package com.demo.auth.controller;

import com.demo.auth.entity.TUserRole;
import com.demo.auth.service.TUserRoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户和角色关联表(TUserRole)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@RestController
@RequestMapping("tUserRole")
public class TUserRoleController {
    /**
     * 服务对象
     */
    @Resource
    private TUserRoleService tUserRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public TUserRole selectOne(Long id) {
        return this.tUserRoleService.queryById(id);
    }

    /**
     * 查询全部数据
     *
     * @return 全部数据
     */
    @GetMapping("findAll")
    public List<TUserRole> findAll() {
        return this.tUserRoleService.queryAllByLimit(1, -1);
    }

}