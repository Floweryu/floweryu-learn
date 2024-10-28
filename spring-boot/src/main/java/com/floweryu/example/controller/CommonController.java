package com.floweryu.example.controller;

import com.floweryu.example.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zhangjunfeng
 * @Date 2024/10/19 16:02
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    @Autowired
    private CommonService commonService;

    @GetMapping("/cache")
    public String cacheQuery(Integer id) {
        return commonService.cacheGet(id);
    }

}
