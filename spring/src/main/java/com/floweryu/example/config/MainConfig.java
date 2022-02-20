package com.floweryu.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

/**
 * @author Floweryu
 * @date 2022/2/20 16:31
 */
@Configuration
@ComponentScan(value = "com.floweryu.example", includeFilters = {
        @ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)
}, useDefaultFilters = false)
public class MainConfig {
    
}
