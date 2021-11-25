package com.floweryu.reflect;

import com.floweryu.reflect.abstracts.AbstractExample;
import com.floweryu.reflect.abstracts.InterfaceExample;
import org.junit.Test;

import java.lang.reflect.Modifier;

/**
 * @author zhangjunfeng
 * @date 2021/11/24 17:20
 */
public class AbstractTest {
    /**
     * 检测抽象类是不是abstract
     * 输出: true
     */
    @Test 
    public void givenAbstractClass_whenCheckModifierIsAbstract_thenTrue() throws Exception {
        Class<AbstractExample> clazz = AbstractExample.class;

        System.out.println(Modifier.isAbstract(clazz.getModifiers()));
    }

    /**
     * 检测接口是不是abstract
     * 输出: true
     */
    @Test
    public void givenInterface_whenCheckModifierIsAbstract_thenTrue() {
        Class<InterfaceExample> clazz = InterfaceExample.class;

        System.out.println(Modifier.isAbstract(clazz.getModifiers()));
    }
}
