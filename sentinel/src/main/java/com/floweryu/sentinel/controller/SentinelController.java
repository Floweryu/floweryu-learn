package com.floweryu.sentinel.controller;

import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Floweryu
 * @date 2022/6/26 21:38
 */

@RestController
public class SentinelController {
    
    @SentinelResource(value = "hello", blockHandler = "helloBlock")
    @GetMapping("/hello")
    public String hello(String name) throws BlockException {
        SphU.entry("sss");
        return "hello" + name;
    }
    
    public String helloBlock(String name, BlockException ex) {
        return "error" + ex;
    }
}
