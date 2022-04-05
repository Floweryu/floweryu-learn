package com.floweryu.example.config;

import com.floweryu.example.bean.ZookeeperProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhangjunfeng
 * @date 2022/3/1 16:25
 */
@Configuration
@EnableConfigurationProperties(ZookeeperProperties.class)
@Slf4j
public class ZookeeperConfig {
    
    @Autowired
    ZookeeperProperties zookeeperProperties;
    
    @Bean("zookeeper")
    public CuratorFramework zkInit() {
        CuratorFramework curatorFramework;
        try {
            curatorFramework = CuratorFrameworkFactory.builder()
                    .connectString(zookeeperProperties.getAddress())
                    .sessionTimeoutMs(zookeeperProperties.getTimeout())
                    .connectionTimeoutMs(zookeeperProperties.getTimeout())
                    .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                    .build();
            curatorFramework.start();
            log.info("初始化ZooKeeper连接状态....={}", curatorFramework.getState());
            return curatorFramework;
        } catch (Exception e) {
            log.error("初始化ZooKeeper连接异常....", e);
            return null;
        }
    }
    
}
