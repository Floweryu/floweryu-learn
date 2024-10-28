package com.floweryu.example.service;

import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.floweryu.example.annotation.MyAnnotation;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Floweryu
 * @date 2023/2/26 21:28
 */
@Service
public class CommonService {
    
    @MyAnnotation
    public Map testAnn() {
        Map map = new HashMap<>();
        map.put("v1", "service中设置的值");
        return map;
    }

    @Cached(name = "cacheGet", key = "#id", expire = 20, cacheType = CacheType.LOCAL)
    public String cacheGet(Integer id) {
        System.out.println("没走缓存");
        return "cacheGet" + id;
    }
}
