package com.floweryu.example.config;

import com.floweryu.example.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Floweryu
 * @date 2022/3/6 21:23
 * 使用PropertySource读取外部配置文件中的k/v属性保存到运行的环境变量中
 */
@PropertySource(value = {"classpath:/person.properties"})
@Configuration
public class MainConfigOfProperty {
    
    @Bean
    public Person person() {
        return new Person();
    }
}
