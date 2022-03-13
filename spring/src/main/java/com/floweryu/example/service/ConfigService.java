package com.floweryu.example.service;

import com.floweryu.example.dao.ConfigDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * @author Floweryu
 * @date 2022/2/20 19:21
 */
@Service
public class ConfigService {
    
    @Inject
    private ConfigDao configDao;
    
    
    public void print() {
        System.out.println(configDao);
    }

    @Override
    public String toString() {
        return "ConfigService{" +
                "configDao=" + configDao +
                '}';
    }
}
