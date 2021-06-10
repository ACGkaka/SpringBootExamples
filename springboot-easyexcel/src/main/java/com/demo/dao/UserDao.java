package com.demo.dao;

import com.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * <p> @Title UserDao
 * <p> @Description 用户Dao
 *
 * @author ACGkaka
 * @date 2021/6/9 13:43
 */
public class UserDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDao.class);

    /**
     * 批量保存用户
     *
     * @param list 用户集合
     */
    public void save(List<User> list) {
        for (int i = 0; i < list.size(); i++) {
            LOGGER.info("user{}: {}", i, list.get(i));
        }
    }
}
