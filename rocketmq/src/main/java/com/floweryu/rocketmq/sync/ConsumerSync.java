package com.floweryu.rocketmq.sync;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/12/2 23:06
 */
public class ConsumerSync {

    public static void main(String[] args) throws Exception{
        // 1.创建消费者Consumer，制定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("demo_consumer_group");
        
        // 2.指定Nameserver地址
        consumer.setNamesrvAddr("106.15.42.148:9876");
        
        // 3. 消息拉取的最大条数
        consumer.setConsumeMessageBatchMaxSize(2);
        
        // 4. 订阅主题和tag
        consumer.subscribe("Topic_demo_sync", "*");
        
        // 5. 设置回调函数处理消息
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
