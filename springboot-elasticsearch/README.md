# Elasticsearch

> **Elasticsearch** 是一个实时的分布式`搜索分析引擎`，它能让你以前所未有的速度和规模，去探索你的数据。 它被用作`全文检索`、`结构化搜索`、`分析`以及这三个功能的组合。
> * 端口: 9200
> * 下载: [https://www.elastic.co/cn/downloads/elasticsearch](https://www.elastic.co/cn/downloads/elasticsearch)
> * 官方文档(英文): [https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html](https://www.elastic.co/guide/en/elasticsearch/reference/current/index.html)
> * 官方文档(中文): [https://www.elastic.co/guide/cn/elasticsearch/guide/current/index.html](https://www.elastic.co/guide/cn/elasticsearch/guide/current/index.html)

## Elasticsearch7 的改动：
* 去除了Type；
* <s>TransportClient</s> -> RestHighLevelClient；
* <s>ElasticsearchTemplate</s> -> ElasticsearchRestTemplate.

## ES的两种调用方式
* 使用`RestHighLevelClient`，示例：src/test/java/com/demo/ElasticsearchUtilTest.java;
* 使用`ElasticsearchRepository`，示例：src/test/java/com/demo/ElasticsearchTest.java.