package com.floweryu.example.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Floweryu
 * @date 2021/12/30 23:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    
    private Integer id;
    
    private String userId;
    
    private String name;
    
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }
}
