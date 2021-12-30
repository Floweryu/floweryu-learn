package com.floweryu.example.mybatis.service;

import com.floweryu.example.mybatis.entity.User;
import com.floweryu.example.mybatis.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Floweryu
 * @date 2021/12/30 23:27
 */
@Service
@Slf4j
public class UserService {
    
    @Autowired
    UserMapper userMapper;
    
    public boolean insertUser(User user) {
        try {
            return userMapper.insert(user) > 0;
        } catch (Exception e) {
            log.error("insert error. ", e);
        }
        return false;
    }
    
    public List<User> listUser() {
        return userMapper.list();
    }
}
