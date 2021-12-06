package com.floweryu.rocketmq.sql;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @author zhangjunfeng
 * @date 2021/12/6 16:56
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("sql_producer_group");
        producer.setNamesrvAddr("106.15.42.148:9876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            /**
             * 4.创建消息对象，指定主题Topic、Tag和消息体
             * 参数一：消息主题Topic
             * 参数二：消息Tag
             * 参数三：消息内容
             */
            Message message = new Message("TopicSQL", "SQL", ("hello .... " + i).getBytes(StandardCharsets.UTF_8));
            message.putUserProperty("a", String.valueOf(i));
            SendResult sendResult = producer.send(message);
            System.out.println("发送结果： " + sendResult);
        }
    }
}
