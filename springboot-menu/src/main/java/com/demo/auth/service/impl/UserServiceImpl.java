package com.demo.auth.service.impl;

import com.demo.auth.entity.User;
import com.demo.auth.mapper.UserMapper;
import com.demo.auth.service.UserService;
import com.demo.common.exception.BaseException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User queryById(Long id) {
        // 根据 ID 查找
        return  userMapper.queryById(id);
    }

    @Override
    public PageInfo<User> queryByPage(int pageNum, int pageSize) {
        // 分页查找
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.queryAll();
        return new PageInfo<>(list);
    }

    @Override
    public List<User> queryAll() {
        // 查询全部
        return userMapper.queryAll();
    }

    @Override
    public User insert(User user) {
        // 新增用户
        User query = new User();
        query.setUsername(user.getUsername());
        List<User> list = userMapper.query(query);
        if (list != null && list.size() > 0) {
            throw new BaseException("用户名已存在");
        }
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        // 编辑用户
        User result = userMapper.queryById(user.getId());
        if (result == null) {
            throw new BaseException("用户不存在");
        }
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
        return  queryById(user.getId());
    }

    @Override
    public void deleteById(Long id) {
        // 删除用户
        User user = userMapper.queryById(id);
        if (user == null) {
            throw new BaseException("用户不存在");
        }
        userMapper.deleteById(id);
    }
}