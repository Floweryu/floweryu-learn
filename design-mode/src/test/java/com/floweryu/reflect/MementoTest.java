package com.floweryu.reflect;

import com.floweryu.memento.TextEditor;
import com.floweryu.memento.TextWindow;
import org.junit.Test;

/**
 * @author Floweryu
 * @date 2021/11/28 11:10
 */
public class MementoTest {
    @Test
    public void testMemento() {
        TextEditor textEditor = new TextEditor(new TextWindow());
        textEditor.getTextWindow().addText("The Memento Design Pattern\n");
        textEditor.getTextWindow().addText("How to implement it in Java?\n");
        textEditor.hitSave();

        textEditor.getTextWindow().addText("Buy milk and eggs before coming home\n");
        System.out.println(textEditor.getTextWindow().getCurrentText());

        textEditor.hitUndo();
        
        System.out.println(textEditor.getTextWindow().getCurrentText());
    }
}
