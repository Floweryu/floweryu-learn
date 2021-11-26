package com.floweryu.reflect;

import com.floweryu.reflect.abstracts.AbstractExample;
import com.floweryu.reflect.abstracts.InterfaceExample;
import org.junit.Test;

import java.lang.reflect.Modifier;
import java.util.Date;

/**
 * @author zhangjunfeng
 * @date 2021/11/24 17:20
 */
public class AbstractTest {
    /**
     * 检测抽象类
     * 输出: true
     */
    @Test 
    public void givenAbstractClass_whenCheckModifierIsAbstract_thenTrue() throws Exception {
        Class<AbstractExample> clazz = AbstractExample.class;

        System.out.println(Modifier.isAbstract(clazz.getModifiers()));
    }

    /**
     * 检测接口
     * 输出: true true
     */
    @Test
    public void givenInterface_whenCheckModifierIsAbstract_thenTrue() {
        Class<InterfaceExample> clazz = InterfaceExample.class;
        int mod = clazz.getModifiers();
        System.out.println(Modifier.isInterface(mod));  // true
        System.out.println(Modifier.isAbstract(clazz.getModifiers()));  // true
    }

    /**
     * 测试抽象类
     */
    @Test
    public void givenAbstractClass_whenCheckIsAbstractClass_thenTrue() {
        Class<AbstractExample> clazz = AbstractExample.class;
        int mod = clazz.getModifiers();

        System.out.println(Modifier.isInterface(mod));  // false
        System.out.println(Modifier.isAbstract(mod));   // true
    }

    /**
     * 测试实现类
     * 返回false, false
     */
    @Test
    public void givenConcreteClass_whenCheckIsAbstractClass_thenFalse() {
        Class<Date> clazz = Date.class;
        int mod = clazz.getModifiers();

        System.out.println(Modifier.isInterface(mod));  // false
        System.out.println(Modifier.isAbstract(mod));   // false
    }
}
