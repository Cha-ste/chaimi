package com.ocean.kafka;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * 订单消费者
 */
@Component
public class KafkaOrdersConsumerService {
    @Autowired

    @KafkaListener(topics = {"orderCreate"}, groupId = "default-group")
    public void listen(ConsumerRecord<String, String> record, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        System.out.println("消费端消费消息：" + record.value());
        // 手动确认
        acknowledgment.acknowledge();
    }
}
