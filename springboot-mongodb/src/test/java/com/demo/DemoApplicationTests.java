package com.demo;

import com.demo.mongodb.dao.DemoDao;
import com.demo.mongodb.entity.DemoEntity;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DemoDao demoDao;

    @Test
    void saveDemoTest() {

        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setTitle("Title Test");

        demoDao.saveDemo(demoEntity);

        demoEntity = new DemoEntity();
        demoEntity.setId(2L);
        demoEntity.setTitle("Title Test");
        demoEntity.setDescription("Description Test");

        demoDao.saveDemo(demoEntity);
    }

    @Test
    void removeDemoTest() {
        demoDao.removeDemo(2L);
    }

    @Test
    void updateDemoTest() {

        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setId(1L);
        demoEntity.setTitle("Title Test 更新数据");
        demoEntity.setDescription("Description Test");

        demoDao.updateDemo(demoEntity);
    }

    @Test
    void findDemoByIdTest() {

        DemoEntity demoEntity = demoDao.findDemoById(1L);

        System.out.println(demoEntity);
    }

}
