package com.floweryu.example.config;

import com.floweryu.example.bean.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Floweryu
 * @date 2022/3/6 14:08
 */
@ComponentScan("com.floweryu.example.bean")
@Configuration
public class MainConfigOfLifeCycle {
    
    @Bean(initMethod = "init", destroyMethod = "destory")
    public Car car() {
        return new Car();
    }
}
