package com.floweryu.example.config;

import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.caffeine.MetricsStatsCounter;
import com.floweryu.example.custom.CustomerStatsCounter;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * @Author zhangjunfeng
 * @Date 2024/10/28 22:20
 */

@Configuration
public class CacheManagerConfig {

    MetricRegistry registry;

    @Primary
    @Bean("baseCacheManager")
    public CacheManager cacheManager() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .maximumSize(1_000)
                        .expireAfterWrite(60, TimeUnit.SECONDS)
                        .recordStats(CustomerStatsCounter::new)
        );
        return cacheManager;
    }

    @Bean("localCacheManagerA")
    public CacheManager localCacheManagerA() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.setCaffeine(
                Caffeine.newBuilder()
                        .maximumSize(1_000)
                        .expireAfterWrite(60, TimeUnit.SECONDS)
        );
        return cacheManager;
    }
}
