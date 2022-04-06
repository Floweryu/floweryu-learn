package com.floweryu.example.zookeeper;

import com.floweryu.example.cache.LocalCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Floweryu
 * @date 2022/4/5 16:28
 */
@Slf4j
public class LocalCacheTest extends BaseTest {

    @Autowired
    LocalCacheUtil localCacheUtil;

    //模拟数据库 key-用户Id, value-存储数据
    private static final Map<String, Map<String,String>> DB = new ConcurrentHashMap<>();
    //模拟数据库数据
    static{
        Map<String,String> map = new HashMap();
        map.put("book", "小王子");
        map.put("name", "哈利波特");
        map.put("userId", "1234");
        DB.put("1234",map);
    }

    @Test
    public void localCacheTest() throws Exception {
        for (int i = 0; i < 2; i++) {
            log.info("查询: result={}", queryUser("1234"));
        }

        //2.测试缓存失效
        Thread.sleep(3000);
        System.out.println("--------------");
        System.out.println("过期后查询："+ queryUser("1234"));
        //3.测试缓存移除
        updateUser("1234","钢铁侠");
        System.out.println("更新后查询："+ queryUser("1234"));
        //4.测试缓存移除
        deleteUser("1234");
        Thread.sleep(Integer.MAX_VALUE);
    }

    private Map<String, String> queryUser(String userId) {
        String group = "user";
        Object value = localCacheUtil.get(group, userId);
        if (value != null) {
            log.info("------缓存命中-----key={}", userId);
            return (Map<String, String>) value;
        }
        log.warn("------缓存未命中------key={}", userId);
        Map<String, String> user = DB.get(userId);
        if (user != null) {
            log.info("------存储到缓存-----key={}", userId);
            localCacheUtil.put(group, userId, user, 1L);
        }
        return user;
    }

    private void updateUser(String userId, String name) {
        String group = "user";
        Map<String, String> user = DB.get(userId);
        if (user == null) {
            throw new RuntimeException("该用户不存在");
        }
        log.info("-------删除缓存, key={}", userId);
        localCacheUtil.remove(group, userId);
        user.put("name", name);
        DB.put(userId, user);
    }

    private void deleteUser(String userId) {
        String group = "user";
        Map<String, String> user = queryUser(userId);
        if (user == null) {
            return;
        }
        log.info("-------删除缓存, key={}", userId);
        localCacheUtil.remove(group, userId);
        DB.remove(userId);
    }
}
