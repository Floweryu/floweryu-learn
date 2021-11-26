package com.floweryu.reflect;

import com.floweryu.reflect.statics.StaticUtility;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjunfeng
 * @date 2021/11/25 20:36
 */
public class StaticTest {

    /**
     * 判断是不是静态方法
     */
    @Test
    public void whenCheckStaticMethod_ThenSuccess() throws Exception {
        Method method = StaticUtility.class.getMethod("getAuthorName", null);
        System.out.println(Modifier.isStatic(method.getModifiers()));   // true
    }

    /**
     * 获取类中的静态方法
     */
    @Test
    public void whenCheckAllStaticMethods_thenSuccess() {
        List<Method> methodList = Arrays.asList(StaticUtility.class.getMethods())
                .stream()
                .filter(method -> Modifier.isStatic(method.getModifiers()))
                .collect(Collectors.toList());
        System.out.println(methodList);
    }
}
