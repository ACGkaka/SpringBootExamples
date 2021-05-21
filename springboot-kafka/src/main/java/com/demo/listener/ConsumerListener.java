package com.demo.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka 消费者监听
 * @author ACGkaka
 */
@Component
public class ConsumerListener {
    @KafkaListener(topics = "testTopic",groupId = "testTopic-group")
    public void onMessage(String msg){
        System.out.println("----收到消息："+msg+"----");
    }
}
