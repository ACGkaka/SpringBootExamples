package com.demo.user.service;

import com.demo.user.entity.User;
import org.springframework.stereotype.Service;

/**
 * <p> @Title UserService
 * <p> @Description 用户 业务层
 *
 * @author ACGkaka
 * @date 2021/5/6 16:04
 */
@Service
public class UserService {

    public User findUser() {
        return new User(1, "ACGkaka", "123456", "123@123.com");
    }

}
