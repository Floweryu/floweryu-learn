package com.floweryu.example.bean;

import lombok.ToString;

/**
 * @author Floweryu
 * @date 2022/1/10 23:13
 */
@ToString
public class Book {
    private String bname;
    
    private String bauthor;

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
    
    /**
     * set方式注入
     */
    public void setBname(String bname) {
        this.bname = bname;
    }
}
