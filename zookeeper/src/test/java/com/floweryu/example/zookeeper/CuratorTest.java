package com.floweryu.example.zookeeper;

import com.floweryu.example.bean.factory.ClientFactory;
import com.floweryu.example.demo.ZkWatcherDemo;
import com.floweryu.example.utils.IdMakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author zhangjunfeng
 * @date 2022/3/28 11:27
 */
@Slf4j
public class CuratorTest extends BaseTest {
    
    public static final String ZK_ADDRESS = "106.15.42.148:2181";
    
    @Test
    public void createNode() {
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        try {
            client.start();
            String data = "hello";
            byte[] payload = data.getBytes(StandardCharsets.UTF_8);
            String path = "/curator/test1/sub1";
            client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, payload);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
        
    }
    
    @Test
    public void readNode() {
        CuratorFramework client = ClientFactory.createSimple(ZK_ADDRESS);
        try {
            client.start();
            String path = "/curator/test1";
            Stat stat = client.checkExists().forPath(path);
            if (null != stat) {
                byte[] bytes = client.getData().forPath(path);
                String data = new String(bytes, StandardCharsets.UTF_8);
                log.info("read data:{}", data);
                List<String> childrens = client.getChildren().forPath(path);
                for (String children : childrens) {
                    log.info("children data:{}", children);
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            CloseableUtils.closeQuietly(client);
        }
    }
    
    
    @Test
    public void makeIdTest() {
        for (int i = 0; i < 10; i++) {
            String uuid = IdMakeUtil.makeId("/uuid/id-" + i);
            System.out.println(uuid);
        }
        
    }
    
    @Test
    public void watcherTest() {
        ZkWatcherDemo zkWatcherDemo = new ZkWatcherDemo();
        zkWatcherDemo.childrenCache();
    }
}
