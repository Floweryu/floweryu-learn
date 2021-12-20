package com.floweryu.rocketmq.transaction;

import com.floweryu.rocketmq.common.RocketMQConstants;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author zhangjunfeng
 * @date 2021/12/6 20:22
 * 使用RocketMQ事务消息——订单服务发送事务消息,然后进行本地下单,并通知商品服务减库存
 */
public class TransactionProducer {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        TransactionMQProducer producer = new TransactionMQProducer("transaction_producer_group");
        producer.setNamesrvAddr(RocketMQConstants.ROCKETMQ_NAMESRV_ADDR);

        ExecutorService executorService = new ThreadPoolExecutor(2, 5, 100, TimeUnit.SECONDS, new ArrayBlockingQueue<>(2000),
                r -> {
                    Thread thread = new Thread(r);
                    thread.setName("client-transaction-msg-check-thread");
                    return thread;
                });

        producer.setExecutorService(executorService);

        TransactionListener transactionListener = new TransactionListenerImpl();
        producer.setTransactionListener(transactionListener);
        producer.start();
        
        String[] tags = new String[] {"TagA", "TagB", "TagC", "TagD", "TagE"};
        for (int i = 0; i < 10; i++) {
            try {
                String orderId = UUID.randomUUID().toString();
                String payload = "下单, orderId: " + orderId;
                Message msg =
                        new Message("TopicTransaction", tags[i % tags.length], orderId, payload.getBytes(RemotingHelper.DEFAULT_CHARSET));
                // 发送事务消息
                SendResult sendResult = producer.sendMessageInTransaction(msg, orderId);
                System.out.printf("发送事务消息结果: %s%n", sendResult);
                Thread.sleep(10);
            } catch (MQClientException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        producer.shutdown();
    }
}
