package com.demo.dao;

import com.demo.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p> @Title BookRepository
 * <p> @Description Book业务层
 *
 * @author zhj
 * @date 2021/5/24 15:22
 */
@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

    /**
     * 根据名称查找
     *
     * @param name 名称
     * @return 用户集合
     */
    List<User> findByName(String name);
}
