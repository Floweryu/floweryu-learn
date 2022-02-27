package com.floweryu.example.config;

import com.floweryu.example.bean.Book;
import com.floweryu.example.bean.Color;
import com.floweryu.example.bean.ColorFactoryBean;
import com.floweryu.example.bean.Red;
import com.floweryu.example.condition.LinuxCondition;
import com.floweryu.example.condition.MyImportBeanDefinitionRegistrar;
import com.floweryu.example.condition.MyImportSelector;
import com.floweryu.example.condition.WindowsCondition;
import org.springframework.context.annotation.*;

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
    
    @Conditional({WindowsCondition.class})
    @Bean("windows")
    public Book book1() {
        return new Book("Windows", "11");
    }

    @Conditional({LinuxCondition.class})
    @Bean("linux")
    public Book book2() {
        return new Book("linux", "8");
    }
    
    @Bean
    public ColorFactoryBean colorFactoryBean() {
        return new ColorFactoryBean();
    }
}
