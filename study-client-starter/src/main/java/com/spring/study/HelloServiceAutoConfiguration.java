package com.spring.study;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Floweryu
 * @date 2022/7/31 15:35
 */
@Configuration
@ComponentScan({"com.spring.study.module"})
public class HelloServiceAutoConfiguration {
}
