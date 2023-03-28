package com.floweryu.example.service;

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
}
