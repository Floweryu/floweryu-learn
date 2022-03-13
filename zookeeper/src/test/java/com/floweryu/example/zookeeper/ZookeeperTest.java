package com.floweryu.example.zookeeper;

import com.floweryu.example.ZookeeperApplication;
import com.floweryu.example.bean.ZkClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhangjunfeng
 * @date 2022/3/4 10:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZookeeperApplication.class)
public class ZookeeperTest {
    
    @Autowired
    private ZkClient zkClient;
    
    @Test
    public void createNodeTest() {
        String zhang = zkClient.createNode("/floweryu/one", "zhang");
        System.out.println(zhang);
    }
}
