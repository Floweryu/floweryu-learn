package com.floweryu.example.service;

import com.floweryu.example.FloweryuApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

/**
 * @author Floweryu
 * @date 2023/2/26 21:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = FloweryuApplication.class)
public class CommonServiceTest {
    @Autowired
    private CommonService commonService;
    
    @Test
    public void annTest() {
        Map map = commonService.testAnn();
        System.out.println(map);
        
    }
}
