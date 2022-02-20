package com.floweryu.example.config;

import com.floweryu.example.bean.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Floweryu
 * @date 2022/2/20 22:46
 */
@Configuration
public class MainConfig2 {
    
    @Scope("prototype")
    @Bean("book")
    public Book book() {
        System.out.println("book is load...");
        return new Book("小王子", "猫儿");
    }
}
