package com.floweryu.example.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Floweryu
 * @date 2022/4/5 10:51
 */
@Slf4j
@Component
public class LocalCacheWatcher implements CuratorWatcher {

    @Autowired
    private LocalCacheUtil localCacheUtil;

    public static LocalCacheWatcher getInstance() {
        return LocalCacheWatcherInstance.LOCAL_CACHE_WATCHER;
    }

    @Override
    public void process(WatchedEvent watchedEvent) throws Exception {
        log.info("收到监听事件: {}", watchedEvent);

        if (watchedEvent.getType() == Watcher.Event.EventType.None) {
            if (watchedEvent.getState() == Watcher.Event.KeeperState.Expired) {
                log.info("清除全部缓存.");
                localCacheUtil.removeAllLocal();
            }
            return;
        }

        String path = watchedEvent.getPath();
        String[] strings = ZkPathUtil.splitPath(path);
        log.info("监听到的节点信息, group={}, key={}", strings[0], strings[1]);
        // 删除本地缓存
        localCacheUtil.removeLocal(strings[0], strings[1]);
    }

    private static class LocalCacheWatcherInstance {
        private static final LocalCacheWatcher LOCAL_CACHE_WATCHER = new LocalCacheWatcher();
    }
}
