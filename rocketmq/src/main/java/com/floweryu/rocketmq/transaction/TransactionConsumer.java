package com.floweryu.rocketmq.transaction;

import com.floweryu.rocketmq.common.RocketMQConstants;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;

/**
 * @author Floweryu
 * @date 2021/12/19 20:24
 * 只有事务执行成功的订单才会通知商品服务进行减库存
 */
public class TransactionConsumer {
    public static void main(String[] args) throws Exception {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("transaction_consumer_group");
        consumer.setNamesrvAddr(RocketMQConstants.ROCKETMQ_NAMESRV_ADDR);
        consumer.subscribe(RocketMQConstants.ROCKETMQ_TRANSACTION_TOPIC, "*");
        consumer.registerMessageListener(new ConsumerListener());
        consumer.start();
        System.out.println("Consumer start");
    }
}
