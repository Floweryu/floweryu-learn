package com.floweryu.example.config;

import com.floweryu.example.aop.LogAspects;
import com.floweryu.example.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Floweryu
 * @date 2022/3/13 11:19
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MainConfigOfAop {
    
    @Bean
    public MathCalculator mathCalculator() {
        return new MathCalculator();
    }
    
    
    @Bean
    public LogAspects logAspects() {
        return new LogAspects();
    }
}
