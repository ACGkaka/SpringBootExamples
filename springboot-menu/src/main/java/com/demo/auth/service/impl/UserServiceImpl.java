package com.demo.auth.service.impl;

import com.demo.auth.entity.User;
import com.demo.auth.mapper.UserMapper;
import com.demo.auth.service.UserService;
import com.demo.common.exception.BaseException;
import com.demo.util.AESUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户表(User)表服务实现类
 *
 * @author ACGkaka
 * @since 2021-06-18 19:11:04
 */
@Service("tUserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Long id) {
        return  userMapper.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return  userMapper.queryAllByLimit(offset, limit);
    }

    @Override
    public User insert(User user) {
        // 新增用户
        User query = new User();
        query.setUsername(user.getUsername());
        List<User> list = userMapper.queryAll(query);
        if (list != null && list.size() > 0) {
            throw new BaseException("用户名已存在");
        }
        user.setDelFlag("0");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
         userMapper.insert(user);
        return user;
    }

    @Override
    public User update(User user) {
        // 编辑用户
         userMapper.update(user);
        return  queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public void deleteById(Long id) {
        User user = userMapper.queryById(id);
        if (user == null) {
            throw new BaseException("用户不存在");
        }
        user.setDelFlag("1");
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }
}