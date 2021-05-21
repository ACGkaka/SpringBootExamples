package com.demo;

import com.demo.producer.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author demo
 */
@SpringBootTest
class KafkaTests {

    @Autowired
    KafkaProducer producer;

    // 发送消息
    @Test
    void testSendMsg() {
        long time = System.currentTimeMillis();
        System.out.println("----"+time +"，已经发出----");
        producer.send("Message sent succeed, " +time);
    }
}
