package com.floweryu.example.dao;

import org.springframework.stereotype.Repository;

/**
 * @author Floweryu
 * @date 2022/2/20 19:27
 */
@Repository
public class ConfigDao {
    
    private String label = "1";

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return "ConfigDao{" +
                "label='" + label + '\'' +
                '}';
    }
}
