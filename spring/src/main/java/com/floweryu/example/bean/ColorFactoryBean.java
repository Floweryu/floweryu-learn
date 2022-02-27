package com.floweryu.example.bean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author Floweryu
 * @date 2022/2/27 21:39
 */
public class ColorFactoryBean implements FactoryBean{
    /**
     * 返回一个color对象，添加到容器中
     */
    @Override
    public Object getObject() throws Exception {
        System.out.println("getObject ...");
        return new Color();
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }
}
