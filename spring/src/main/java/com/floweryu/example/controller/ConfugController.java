package com.floweryu.example.controller;

import com.floweryu.example.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @author Floweryu
 * @date 2022/2/20 19:20
 */
@Controller
public class ConfugController {
    
    @Autowired
    private ConfigService configService;
}
