package com.floweryu.example.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author Floweryu
 * @date 2022/3/6 14:04
 */
@Component
public class LocalCacheUtil {

    @Autowired
    private ZkClient zkClient;

    /**
     * key-分组名
     * value-每个组对应一个cache
     */
    private static Map<String, Cache<String, Object>> cacheMap = new ConcurrentHashMap<>();

    /**
     * 获取该组的cache
     * @param group
     * @param expire
     * @return
     */
    private Cache<String, Object> getCache(String group, Long expire) {
        Cache<String, Object> cache = cacheMap.get(group);
        if (cache == null) {
            cache = CacheBuilder.newBuilder()
                    .expireAfterWrite(expire, TimeUnit.SECONDS)
                    .maximumSize(1000)
                    .build();
            cacheMap.put(group, cache);
        }
        return cache;
    }

    /**
     * 获取缓存
     * @param key
     * @return
     */
    public Object get(String group, String key) {
        Cache cache = cacheMap.get(group);
        if (cache == null) {
            return null;
        }
        return cache.getIfPresent(key);
    }


    /**
     * 写入缓存
     * @param group 分组
     * @param key
     * @param val
     * @param expire 过期时间
     */
    public void put(String group, String key, Object val, Long expire) {
        Cache<String, Object> cache = getCache(group, expire);
        cache.put(key, val);
        zkClient.setWatcher(group, key, LocalCacheWatcher.getInstance());
    }

    /**
     * 删除缓存 节点和本地缓存都删除
     * @param group
     * @param key
     */
    public void remove(String group, String key) {
        Cache<String, Object> cache = cacheMap.get(group);
        if (cache != null) {
            cache.invalidate(key);
        }
        zkClient.delete(group, key);
    }

    /**
     * 只删除本地缓存
     * @param group
     * @param key
     */
    public void removeLocal(String group, String key) {
        Cache<String, Object> cache = cacheMap.get(group);
        if (cache != null) {
            cache.invalidate(key);
        }
    }

    /**
     * 删除所有本地缓存
     */
    public void removeAllLocal() {
        for (String item : cacheMap.keySet()) {
            Cache<String, Object> cache = cacheMap.get(item);
            if (cache != null) {
                cache.invalidateAll();
            }
        }
    }
}
