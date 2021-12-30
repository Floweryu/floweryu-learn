package com.floweryu.example.mybatis.controller;

import com.floweryu.example.mybatis.entity.User;
import com.floweryu.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/12/30 23:46
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @GetMapping("/get")
    public List<User> getUser() {
        return userService.listUser();
    }
}
