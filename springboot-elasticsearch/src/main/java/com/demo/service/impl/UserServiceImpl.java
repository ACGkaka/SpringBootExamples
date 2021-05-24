package com.demo.service.impl;

import com.demo.dao.UserRepository;
import com.demo.entity.User;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <p> @Title UserServiceImpl
 * <p> @Description 用户ServiceImpl
 *
 * @author zhj
 * @date 2021/5/24 15:26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String id) {
        Optional<User> optional = userRepository.findById(id);
        return optional.orElse(new User());
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
