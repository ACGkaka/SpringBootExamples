package com.demo;

import com.alibaba.fastjson.JSON;
import com.demo.entity.User;
import com.demo.utils.ElasticsearchUtil;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> @Title ElasticsearchUtilTests
 * <p> @Description Elasticsearch 测试类
 *
 * @author ACGkaka
 * @date 2021/5/21 17:40
 */
@SpringBootTest
public class ElasticsearchUtilTests {

    @Autowired
    private ElasticsearchUtil elasticsearchUtil;

    /**
     * 功能描述：createIndex 测试索引的创建 <br>
     *     注：重复创建会抛异常
     */
    @Test
    void createIndexTest() throws IOException {
        CreateIndexResponse response = elasticsearchUtil.createIndex("my_index");
        System.out.println(JSON.toJSONString(response));
    }

    /**
     * 功能描述：existsIndexTest 判断索引是否存在 <br>
     */
    @Test
    void existsIndexTest() throws IOException {
        boolean exists = elasticsearchUtil.existsIndex("my_index");
        System.out.println(exists);
        if (!exists) {
            System.out.println(">>>>>>>>> 索引不存在。。。。。");
        }
    }

    /**
     * 功能描述：deleteIndexTest 删除指定的索引 <br>
     */
    @Test
    void deleteIndexTest() throws IOException {
        AcknowledgedResponse result = elasticsearchUtil.deleteIndex("my_index");
        System.out.println(JSON.toJSON(result));
    }

    /**
     * 功能描述：创建文档 <br>
     *     注：存在即更新，不指定ID会自动创建
     */
    @Test
    void addDocumentTest() throws IOException {
        // 此处ID不会作为主键存储，会作为单独的域
        User user = new User("1", "ACGkaka123", 27);

        IndexResponse index = elasticsearchUtil.addDocument("my_index", user);
        System.out.println(JSON.toJSONString(index));
    }

    /**
     * 功能描述：getDocumentTest 获取文档信息 <br>
     */
    @Test
    void getDocumentTest() throws IOException {
        GetResponse response = elasticsearchUtil.getDocument("my_index", "RTM_nXkBoUnc6KHbrTdf");
        System.out.println(JSON.toJSONString(response));
        System.out.println(response.getSourceAsString());
    }

    /**
     * 功能描述：updateDocmentTest 更新文档信息 <br>
     *     注：不存在抛异常
     */
    @Test
    void updateDocumentTest() throws IOException {
        // 此处ID不会作为主键存储，会作为单独的域
        User user = new User(null, "小明", 28);

        UpdateResponse updateResponse = elasticsearchUtil.updateDocument("my_index", "1", user);
        System.out.println(JSON.toJSONString(updateResponse));
    }

    /**
     * 功能描述：deleteDocmentTest 删除文档信息 <br>
     */
    @Test
    void deleteDocumentTest() throws IOException {
        DeleteResponse response = elasticsearchUtil.deleteDocument("my_index", "1");
        System.out.println(JSON.toJSONString(response));
    }

    /**
     * 功能描述：addDocmentByBatchTest 批量创建文档 <br>
     */
    @Test
    void addDocmentByBatchTest() throws IOException {
        List<User> list = new ArrayList<>();
        List<String> idList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            idList.add(String.valueOf(i));
            // 此处ID不会作为主键存储，会作为单独的域
            list.add(new User(null, "姓名" + i, 20+i));
        }

        BulkResponse responses = elasticsearchUtil.addDocmentByBatch("my_index", idList, list);
        System.out.println(responses.hasFailures());    // 是否失败，如果false则表示全部成功
        System.out.println(JSON.toJSONString(responses));
    }

    /**
     * 功能描述：searchTest 批量搜索，可以设置高亮等信息 <br>
     */
    @Test
    void searchTest() throws IOException {
        String search = "ACGkaka";
        SearchResponse response1 = elasticsearchUtil.searchBlur("my_index", "name", search);
        SearchResponse response2 = elasticsearchUtil.searchExact("my_index", "name", search);
        System.out.println("模糊查询" + JSON.toJSONString(response1.getHits().getHits()));
        System.out.println("精确查询" + JSON.toJSONString(response2.getHits().getHits()));
    }

}
