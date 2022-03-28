package com.floweryu.example.bean.factory;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author zhangjunfeng
 * @date 2022/3/28 11:10
 */
public class ClientFactory {
    /**
     * 创建curator实例
     * @param connectString zk连接地址
     * @return
     */
    public static CuratorFramework createSimple(String connectString) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        return CuratorFrameworkFactory.newClient(connectString, retryPolicy);
    }

    /**
     * 创建实例
     * @param connectString zk连接地址
     * @param retryPolicy   重试策略
     * @param connectTimeoutMs  连接超时时间
     * @param sessionTimeoutMs  会话超时时间
     * @return
     */
    public static CuratorFramework createWithOptions(String connectString, RetryPolicy retryPolicy, int connectTimeoutMs, int sessionTimeoutMs) {
        return CuratorFrameworkFactory.builder()
                .connectString(connectString)
                .retryPolicy(retryPolicy)
                .connectionTimeoutMs(connectTimeoutMs)
                .sessionTimeoutMs(sessionTimeoutMs)
                .build();
    }
}
