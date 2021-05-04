package com.demo.mongodb.dao;

import com.demo.mongodb.entity.DemoEntity;

/**
 * <p> @Title DemoDao
 * <p> @Description 增删改查 MongoDB 接口
 *
 * @author ACGkaka
 * @date 2021/4/23 14:15
 */
public interface DemoDao {

    void saveDemo(DemoEntity demoEntity);

    void removeDemo(Long id);

    void updateDemo(DemoEntity demoEntity);

    DemoEntity findDemoById(Long id);
}
