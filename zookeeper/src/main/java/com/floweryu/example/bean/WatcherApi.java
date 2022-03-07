package com.floweryu.example.bean;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author zhangjunfeng
 * @date 2022/3/4 10:28
 */
@Slf4j
public class WatcherApi implements Watcher {
    
    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("Wathcer events is: {}", watchedEvent.getState());
        log.info("Watcher path is: {}", watchedEvent.getPath());
        log.info("Watcher type is: {}", watchedEvent.getType());
    }
}
