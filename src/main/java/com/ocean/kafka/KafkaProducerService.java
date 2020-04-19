package com.ocean.kafka;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * 订单生产者
 */
@Component
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 往指定会话中发送消息
     *
     * @param topic 会话名称
     * @param object 消息对象
     */
    public void send(String topic, Object object) {
        if (object == null) {
            return;
        }
        String message = JSONObject.toJSONString(object);
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, message);

        // 消息发送后回调函数
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                System.out.println("消息发送失败：" + throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                System.out.println("消息发送成功：" + message);
            }
        });
    }

}
