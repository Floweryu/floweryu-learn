package com.floweryu.example.mybatis;

import com.floweryu.example.FloweryuApplication;
import com.floweryu.example.mybatis.entity.User;
import com.floweryu.example.mybatis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Floweryu
 * @date 2021/12/30 23:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FloweryuApplication.class)
public class MybatisTest {
    
    @Autowired
    UserService userService;
    
    @Test
    public void insertTest() {
        User user = new User("888", "zhangjunfeng");
        boolean flag = userService.insertUser(user);
        System.out.println(flag);
    }
}
