package com.demo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> @Title ElasticsearchConfig
 * <p> @Description Elasticsearch 配置类
 *
 * @author ACGkaka
 * @date 2021/5/21 17:38
 */
@Configuration
public class ElasticsearchConfig {

    @Value("${my.es.hostname:localhost}")
    private String hostname;

    @Value("${my.es.port:9200}")
    private Integer port;

    @Value("${my.es.scheme:http}")
    private String scheme;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(hostname, port, scheme)));
    }
}
