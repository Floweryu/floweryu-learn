package com.floweryu.rocketmq.ondirection;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @author Floweryu
 * @date 2021/12/5 17:08
 */
public class OnewayProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("oneway_producer");
        // 设置Nameserver地址
        producer.setNamesrvAddr("106.15.42.148:9876");
        
        // 启动生产者
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ" + i).getBytes(StandardCharsets.UTF_8));
            // 发送单向消息
            producer.sendOneway(msg);
        }
        producer.shutdown();
    }
}
