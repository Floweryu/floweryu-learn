package com.floweryu.rocketmq.transaction;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @author Floweryu
 * @date 2021/12/19 20:27
 */
public class ConsumerListener implements MessageListenerConcurrently {
    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
        Optional.ofNullable(msgs).orElse(Collections.emptyList()).forEach(m -> {
            String orderId = m.getKeys();
            System.out.println("监听到下单消息,orderId: " + orderId + ", 商品服务减库存");
        });
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}
