package com.floweryu.example.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhangjunfeng
 * @date 2022/3/1 16:31
 */
@Data
@ConfigurationProperties(prefix = "zookeeper")
public class ZookeeperProperties {

    private String address;
    
    private int timeout;
}
