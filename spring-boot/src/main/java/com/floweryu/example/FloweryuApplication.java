package com.floweryu.example;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Floweryu
 * @date 2021/12/30 23:57
 */
@SpringBootApplication
@EnableCaching
@EnableMethodCache(basePackages = "com.floweryu.example")
@EnableScheduling
public class FloweryuApplication {
    public static void main(String[] args) {
        SpringApplication.run(FloweryuApplication.class);
    }
}
