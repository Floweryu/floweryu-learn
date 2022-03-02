package com.floweryu.example.config;

import com.floweryu.example.bean.ZookeeperProperties;


import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CountDownLatch;

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
    public ZooKeeper zkInit() {
        ZooKeeper zooKeeper = null;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(zookeeperProperties.getAddress(), zookeeperProperties.getTimeout(), watchedEvent -> {
                if (Watcher.Event.KeeperState.SyncConnected == watchedEvent.getState()) {
                    countDownLatch.countDown();
                }
            });
            countDownLatch.await();
            log.info("初始化ZooKeeper连接状态....={}", zooKeeper.getState());
        } catch (Exception e) {
            log.error("初始化ZooKeeper连接异常....", e);
        }
        return zooKeeper;
    }
    
}
