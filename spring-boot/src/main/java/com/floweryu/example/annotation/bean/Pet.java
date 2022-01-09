package com.floweryu.example.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Floweryu
 * @date 2022/1/3 20:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Pet {
    private String name;
    private Integer age;
}
