package com.floweryu.rocketmq.sql;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @author zhangjunfeng
 * @date 2021/12/6 17:10
 */
public class Consumer {
    public static void main(String[] args) throws Exception {
        // 创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("sql_consumer_group");

        // 指定Nameserver地址
        consumer.setNamesrvAddr("106.15.42.148:9876");

        // 订阅主题和tag
        consumer.subscribe("TopicSQL", MessageSelector.bySql("a between 0 and 3"));

        // 设置回调函数处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently() {

            // 接受消息内容   
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt message : list) {
                    try {
                        String topic = message.getTopic();

                        String tags = message.getTags();

                        byte[] body = message.getBody();

                        String result = new String(body, RemotingHelper.DEFAULT_CHARSET);
                        System.out.println("Consumer消费信息：topic:" + topic + ",tags:" + tags + ",result：" + result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
