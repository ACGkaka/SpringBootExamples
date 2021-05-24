package com.demo.utils;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * <p> @Title ElasticsearchUtil
 * <p> @Description Elasticsearch 工具类
 *
 * @author ACGkaka
 * @date 2021/5/21 17:40
 */
@Slf4j
@Component
public class ElasticsearchUtil {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    /**
     * 功能描述：createIndex 测试索引的创建 <br>
     * 注：重复创建会抛异常
     *
     * @param index 索引名称
     * @return 响应信息
     */
    public CreateIndexResponse createIndex(String index) throws IOException {
        if (!existsIndex(index)) {
            // 1. 创建索引请求
            CreateIndexRequest firstIndex = new CreateIndexRequest(index);

            // 2. 客户端执行创建索引的请求
            return restHighLevelClient.indices().create(firstIndex, RequestOptions.DEFAULT);
        } else {
            throw new RuntimeException("索引不存在");
        }
    }

    /**
     * 功能描述：existsIndex 判断索引是否存在 <br>
     *
     * @param index 索引名称
     * @return 是否存在
     */
    public boolean existsIndex(String index) throws IOException {
        // 1. 创建一个get请求获取指定索引的信息
        GetIndexRequest getIndexRequest = new GetIndexRequest(index);

        // 2. 客户端执行请求判断索引是否存在
        return restHighLevelClient.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 功能描述：deleteIndex 删除指定的索引 <br>
     *
     * @param index 索引名称
     * @return 删除结果
     */
    public AcknowledgedResponse deleteIndex(String index) throws IOException {
        // 1. 创建一个delete请求，用于删除索引信息
        DeleteIndexRequest deleteIndexRequest = new DeleteIndexRequest(index);

        // 2. 客户端执行删除请求
        return restHighLevelClient.indices().delete(deleteIndexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 功能描述：创建文档 <br>
     * 注：存在即更新，不指定ID会自动创建
     *
     * @param index  索引名称
     * @param object 待存储数据
     * @return 创建结果
     */
    public IndexResponse addDocument(String index, Object object) throws IOException {
        return addDocument(index, null, object);
    }

    /**
     * 功能描述：创建文档 <br>
     * 注：存在即更新，不指定ID会自动创建
     *
     * @param index      索引名称
     * @param documentId 文档ID
     * @param object     待存储数据
     * @return 创建结果
     */
    public IndexResponse addDocument(String index, String documentId, Object object) throws IOException {
        // 1. 创建请求并指定索引
        IndexRequest indexRequest = new IndexRequest(index);

        // 2. 设置ID
        if (Strings.isNotBlank(documentId)) {
            indexRequest.id(documentId);
        }
        /// 超时时间：在时间范围内搜索到多少数据返回多少数据
        // indexRequest.timeout("1s");

        // 3. 将数据放入到请求中
        indexRequest.source(JSON.toJSONString(object), XContentType.JSON);

        // 4. 使用客户端发送请求
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 功能描述：getDocument 获取文档信息 <br>
     *
     * @param index 索引名称
     * @param id    文档ID
     * @return 文档信息
     */
    public GetResponse getDocument(String index, String id) throws IOException {
        // 1. 创建请求信息绑定索引和指定需要查询的文档id
        GetRequest getRequest = new GetRequest(index, id);

        // 2. 判断指定的索引和id是否存在
        boolean exists = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);

        // 3. 获取指定ID的资源信息
        return exists ? restHighLevelClient.get(getRequest, RequestOptions.DEFAULT) : null;

        /// 4. 获取资源信息
        // response.getSource();
    }

    /**
     * 功能描述：getDocument 获取文档资源信息 <br>
     *
     * @param index 索引名称
     * @param id    文档ID
     * @return 文档资源信息
     */
    public Map<String, Object> getDocumentSource(String index, String id) throws IOException {
        return getDocument(index, id).getSource();
    }

    /**
     * 功能描述：updateDocment 更新文档信息 <br>
     * 注：不存在抛异常
     *
     * @param index  索引名称
     * @param id     文档ID
     * @param object 文档资源
     * @return 更新结果
     */
    public UpdateResponse updateDocument(String index, String id, Object object) throws IOException {

        // 1. 创建一个更新请求的信息，绑定索引名称和需要更新的文档ID
        UpdateRequest updateRequest = new UpdateRequest(index, id);
        /// 超时时间：在时间范围内搜索到多少数据返回多少数据
//        updateRequest.timeout("1s");

        // 2. 封装需要更新的文档信息
        updateRequest.doc(JSON.toJSONString(object), XContentType.JSON);

        // 3. 使用update更新文档
        return restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);

    }

    /**
     * 功能描述：deleteDocment 删除文档信息 <br>
     *
     * @param index 索引名称
     * @param id    文档ID
     * @return 删除结果
     */
    public DeleteResponse deleteDocument(String index, String id) throws IOException {
        // 1. 创建一个删除的请求，绑定索引名和需要删除的文档ID
        DeleteRequest deleteRequest = new DeleteRequest(index, id);

        // 2. 发起删除请求
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    /**
     * 功能描述：addDocmentByBatch 批量创建文档 <br>
     *
     * @param index 索引名称
     * @param objectList 文档数据集合
     * @return 批量创建结果
     */
    public BulkResponse addDocmentByBatch(String index, List<Object> objectList) throws IOException {
        return addDocmentByBatch(index, null, objectList);
    }

    /**
     * 功能描述：addDocmentByBatch 批量创建文档 <br>
     *
     * @param index 索引名称
     * @param idList 文档ID集合
     * @param objectList 文档数据集合
     * @return 批量创建结果
     */
    public <T> BulkResponse addDocmentByBatch(String index, List<String> idList, List<T> objectList) throws IOException {
        // 1. 创建批量的请求
        BulkRequest bulkRequest = new BulkRequest();
        /// 超时时间：在时间范围内搜索到多少数据返回多少数据
//        bulkRequest.timeout("10s");

        // 2. 将多条数据批量的放入bulkRequest中
        if (CollectionUtils.isEmpty(idList)) {

            objectList.forEach(o -> bulkRequest.add(new IndexRequest(index).source(JSON.toJSONString(o), XContentType.JSON)));

        } else if (Objects.equals(idList.size(), objectList.size())) {

            for (int i = 0; i < objectList.size(); i++) {
                // 批量更新和批量删除在这里修改对应的请求即可
                bulkRequest.add(new IndexRequest(index)
                        .id(idList.get(i))
                        .source(JSON.toJSONString(objectList.get(i)), XContentType.JSON));
            }

        } else {
            throw new RuntimeException("【ERROR】ID集合数量 != 资源集合数量");
        }

        // 3. 执行批量创建文档
        BulkResponse response = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        if (response.hasFailures()) {
            log.error("【ERROR】 部分文档创建失败");
        }
        return restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
    }

    /**
     * 功能描述：searchExact 精确查找
     *
     * @param index 索引名称
     * @param field 待查找列
     * @param search 查找内容
     * @return 搜索结果
     */
    public SearchResponse searchExact(String index, String field, String search) throws IOException {
        return search(index, field, search, false);
    }

    /**
     * 功能描述：searchBlur 模糊查找
     *
     * @param index 索引名称
     * @param field 待查找列
     * @param search 查找内容
     * @return 搜索结果
     */
    public SearchResponse searchBlur(String index, String field, String search) throws IOException {
        return search(index, field, String.format("*%s*", search), true);
    }

    /**
     * 功能描述：search 批量搜索，可以设置高亮等信息 <br>
     *
     * @param index 索引名称
     * @param field 待查找列
     * @param search 查找内容
     * @param isBlur 是否为模糊查询
     * @return 搜索结果
     */
    public SearchResponse search(String index, String field, String search, boolean isBlur) throws IOException {

        // 1. 创建批量搜索请求，并绑定索引
        SearchRequest searchRequest = new SearchRequest(index);

        // 2. 构建搜索条件
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder;
        if (Objects.nonNull(search)) {
            if (isBlur) {
                // 设置查询: 模糊查询
                queryBuilder = QueryBuilders.wildcardQuery(String.format("%s.keyword", field), search);
            } else {
                // 设置查询: 精确查询
                // 【注意】：这里有个坑，当中文查询时，如果使用ik分词器会查询不到数据，属性需要使用xxx.keyword才能查询到数据
                queryBuilder = QueryBuilders.termQuery(String.format("%s.keyword", field), search);
            }
        } else {
            // 设置查询: 查询全部
            queryBuilder = QueryBuilders.matchAllQuery();
        }
        sourceBuilder.query(queryBuilder).timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 3. 将查询条件放入搜索请求request中
        searchRequest.source(sourceBuilder);

        // 4. 发起查询请求获取数据
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

}
