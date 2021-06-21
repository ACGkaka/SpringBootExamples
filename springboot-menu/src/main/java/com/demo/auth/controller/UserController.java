package com.demo.auth.controller;

import com.demo.auth.entity.User;
import com.demo.auth.service.UserService;
import com.demo.common.result.BaseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(User)表控制层
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@RestController
@RequestMapping("user")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 用户 - 新增
     */
    @PostMapping("add")
    public BaseResult add(@RequestBody User user) {
        userService.insert(user);
        return BaseResult.success().setData(user);
    }

    /**
     * 用户 - 通过id查询
     */
    @GetMapping("findById")
    public BaseResult findById(Long id) {
        User user = userService.queryById(id);
        return BaseResult.success().setData(user);
    }

    /**
     * 用户 - 编辑保存
     */
    @PostMapping("save")
    public BaseResult save(@RequestBody User user) {
        user = userService.update(user);
        return BaseResult.success().setData(user);
    }

    /**
     * 用户 - 通过id删除
     */
    @DeleteMapping("delete")
    public BaseResult delete(Long id) {
        userService.deleteById(id);
        return BaseResult.success();
    }

    /**
     * 用户 - 分页查询
     */
    @GetMapping("findByPage")
    public BaseResult findByPage(int pageNum, int pageSize) {
        PageInfo<User> pageInfo = userService.queryByPage(pageNum, pageSize);
        return BaseResult.success().setData(pageInfo);
    }

}