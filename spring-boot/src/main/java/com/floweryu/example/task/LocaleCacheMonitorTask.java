package com.floweryu.example.task;

import com.alicp.jetcache.embedded.CaffeineCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @Author zhangjunfeng
 * @Date 2024/10/28 23:15
 */
@Component
public class LocaleCacheMonitorTask {

    @Autowired
    private List<CacheManager> cacheManagerList;

    //@Scheduled(fixedDelay = 10000)
    public void monitor() {
        for (CacheManager cacheManager : cacheManagerList) {
            Collection<String> cacheNameList = cacheManager.getCacheNames();
            for (String cacheName : cacheNameList) {
                Cache cache = cacheManager.getCache(cacheName);
                com.github.benmanes.caffeine.cache.Cache caffeineCache = (com.github.benmanes.caffeine.cache.Cache)cache.getNativeCache();
                CacheStats stats = caffeineCache.stats();
                // 打印统计信息
                System.out.println("Cache: " + cacheName);
                System.out.println("Hit Count: " + stats.hitCount());
                System.out.println("Miss Count: " + stats.missCount());
                System.out.println("Load Success Count: " + stats.loadSuccessCount());
                System.out.println("Load Exception Count: " + stats.loadFailureCount());
                System.out.println("Total Load Time: " + stats.totalLoadTime() / 1000_000);
                System.out.println("Eviction Count: " + stats.evictionCount());
                System.out.println("Average Load Penalty: " + stats.averageLoadPenalty() / 1000_000);
                System.out.println("Hit Rate: " + (stats.hitRate() * 100) + "%");
                System.out.println("Miss Rate: " + (stats.missRate() * 100) + "%");
                System.out.println("----------------------------------------");

            }

        }
    }
}
