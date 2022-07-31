package com.floweryu.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * @author Floweryu
 * @date 2022/7/17 14:53
 */
@Controller
@RequestMapping("/config")
@NacosPropertySource(groupId = "floweryu-learn",dataId = "test",autoRefreshed = true)
public class ConfigController {
    
    @NacosValue(value = "${useLocalCache:false}", autoRefreshed = true)
    private boolean useLocalCache; 
    
    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public Boolean get() {
        return useLocalCache;
    }
}