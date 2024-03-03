package com.floweryu.rocketmq.async;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.CountDownLatch2;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.TimeUnit;

/**
 * @author Floweryu
 * @date 2021/12/5 10:52
 */
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("async_producer_group");
        // 设置Nameserver地址
        producer.setNamesrvAddr("106.15.42.148:9876");
        // 启动producer实例
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);
        
        int messageCount = 100;
        // 根据消息数量实例化倒计时计算器
        final CountDownLatch2 countDownLatch = new CountDownLatch2(messageCount);
        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            // 创建消息，设置topic, tag, 消息体
            Message message = new Message("TopicTest", "TagA", "test1", "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            
            // SendCallback 接收异步返回结果的回调
            producer.setRetryTimesWhenSendAsyncFailed(3);
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable throwable) {
                    countDownLatch.countDown();
                    System.out.printf("%-10d Exception %s %n", index, throwable);
                    throwable.printStackTrace();
                }
            });
        }
        // 等待5s
        countDownLatch.await(5, TimeUnit.SECONDS);
        // 如果不再发送消息，关闭Producer实例
        producer.shutdown();
    }
}
