package com.floweryu.memento;

import lombok.Data;

/**
 * @author Floweryu
 * @date 2021/11/28 11:08
 */
@Data
public class TextWindowState {
    private String text;

    public TextWindowState(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
