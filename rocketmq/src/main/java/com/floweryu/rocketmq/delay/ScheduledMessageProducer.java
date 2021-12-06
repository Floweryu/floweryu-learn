package com.floweryu.rocketmq.delay;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangjunfeng
 * @date 2021/12/6 14:45
 */
public class ScheduledMessageProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("delay_producer_group");
        
        producer.setNamesrvAddr("106.15.42.148:9876");
        
        producer.start();
        
        int totalMessageToSend = 100;
        
        for (int i = 0; i < totalMessageToSend; i++) {
            Message message = new Message("TopicTest", ("Hello scheduled message " + i).getBytes(StandardCharsets.UTF_8));
            // 设置延时等级3，这个消息将在10s后发送（现在只支持固定的几个时间，详情查看delayTimeLevel）
            message.setDelayTimeLevel(3);
            producer.send(message);
        }
        producer.shutdown();
    }
}
