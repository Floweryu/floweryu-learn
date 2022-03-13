package com.floweryu.example.config;

import com.floweryu.example.dao.ConfigDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author Floweryu
 * @date 2022/3/12 22:44
 */
@Configuration
@ComponentScan({"com.floweryu.example.service", "com.floweryu.example.dao", "com.floweryu.example.controller", "com.floweryu.example.bean"})
public class MainConfigOfAutowired {
    
    @Primary
    @Bean("configDao2")
    public ConfigDao configDao() {
        ConfigDao configDao = new ConfigDao();
        configDao.setLabel("2");
        return configDao;
    }
}
