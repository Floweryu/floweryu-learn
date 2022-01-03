package com.floweryu.example.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Floweryu
 * @date 2022/1/3 21:42
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@ConfigurationProperties(prefix = "car")
public class Car {
    private String name;
    
    private Integer price;

    private Pet pet;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
