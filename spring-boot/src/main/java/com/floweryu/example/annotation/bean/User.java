package com.floweryu.example.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Floweryu
 * @date 2022/1/3 19:31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String name;
    
    private Integer age;
    
    private Pet pet;
    
    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
