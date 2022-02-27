package com.floweryu.example.config;

import com.floweryu.example.bean.Book;
import com.floweryu.example.bean.Color;
import com.floweryu.example.bean.Red;
import com.floweryu.example.condition.MyImportBeanDefinitionRegistrar;
import com.floweryu.example.condition.MyImportSelector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;

/**
 * @author Floweryu
 * @date 2022/2/20 22:46
 */
@Configuration
@Import({Color.class, Red.class, MyImportSelector.class, MyImportBeanDefinitionRegistrar.class})
public class MainConfig2 {
    
    @Lazy
    @Bean("book")
    public Book book() {
        System.out.println("向容器中添加book...");
        return new Book("小王子", "猫儿");
    }
}
