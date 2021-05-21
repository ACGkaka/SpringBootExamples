package com.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @author demo
 */
@SpringBootTest
class KafkaTransactionTests {

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;

    // 消费者：先启动 kafkaApp
    //  Must set acks to all in order to use the idempotent producer.
    @Test
    void executeInTransaction() {
        long time = System.currentTimeMillis();
        kafkaTemplate.executeInTransaction(new KafkaOperations.OperationsCallback() {
            @Override
            public Object doInOperations(KafkaOperations kafkaOperations) {
                kafkaOperations.send("springboottopic", "test executeInTransaction");
                // throw new RuntimeException("fail");
                return true;
            }
        });
    }

}