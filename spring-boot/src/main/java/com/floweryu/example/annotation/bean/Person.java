package com.floweryu.example.annotation.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Floweryu
 * @date 2022/1/9 17:01
 */
@ConfigurationProperties(prefix = "person")
@Component
@Data
@ToString
public class Person {
    private String username;
    private List<String> animal;
    private Map<String, Object> score;
    private Set<Double> salarys;
    private Map<String, List<Pet>> allPets;
}
