# Kafka

Zookeeper端口：2181

Kafka端口：9092


启动命令
```cmd
@echo off

title kafka-server
kafka-server-start.bat ../../config/server.properties
```

监听命令
```cmd
kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic testTopic
```

创建Topic
```cmd
kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic testTopic
```

查看Topic
```cmd
kafka-topics.bat --list --zookeeper localhost:2181
```