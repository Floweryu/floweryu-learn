package com.floweryu.example.cache;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangjunfeng
 * @date 2022/3/1 19:54
 */
@Slf4j
@Component
public class ZkClient {

    @Autowired
    private CuratorFramework client;

    /**
     * 删除节点
     * @param group 分组
     * @param key 缓存key
     */
    public void delete(String group, String key) {
        String path = ZkPathUtil.getPath(group, key);
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat != null) {
                client.delete().guaranteed().forPath(path);
            }
        } catch (Exception e) {
            log.error("删除节点失败", e);
        }
    }

    /**
     * 设置监听
     * @param group 分组
     * @param key 缓存Key
     * @param curatorWatcher watcher回调
     */
    public void setWatcher(String group, String key, CuratorWatcher curatorWatcher) {
        String path = ZkPathUtil.getPath(group, key);
        try {
            Stat stat = client.checkExists().forPath(path);
            if (stat == null) {
                client.create().creatingParentsIfNeeded().forPath(path);
            }
            client.checkExists().usingWatcher(curatorWatcher).forPath(path);
        } catch (Exception e) {
            log.error("监听节点失败", e);
        }
    }
}
