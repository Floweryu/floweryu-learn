package com.floweryu.rocketmq.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangjunfeng
 * @date 2021/12/6 20:28
 */
public class TransactionListenerImpl implements TransactionListener {
    private final ConcurrentHashMap<String, Boolean> localTrans = new ConcurrentHashMap<>();

    /**
     * 消息发送成功, 记录本地事务状态
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        String orderId = (String) arg;
        
        // 记录本地事务执行结果
        boolean success = persisTransactionResult(orderId);
        System.err.println("订单服务执行本地事务下单,orderId: " + orderId + ", result: " + success);
        return success ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.ROLLBACK_MESSAGE;
    }

    /**
     * 事务消息状态回查
     * COMMIT_MESSAGE : 提交消息到自定义主题下
     * UNKNOW 或 ROLLBACK_MESSAGE : 消息将会放在 RMQ_SYS_TRANS_HALF_TOPIC 下
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        String orderId = msg.getKeys();
        System.err.println("执行事务消息回查,orderId: " + orderId);
        return localTrans.get(orderId) ? LocalTransactionState.COMMIT_MESSAGE : LocalTransactionState.ROLLBACK_MESSAGE;
    }

    /**
     * 筛选几个成功的事务
     */
    private boolean persisTransactionResult(String orderId) {
        boolean success = Math.abs(Objects.hash(orderId)) % 2 == 0;
        localTrans.put(orderId, success);
        return success;
    }
}
