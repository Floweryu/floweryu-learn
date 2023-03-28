package com.floweryu.example.annotation.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Floweryu
 * @date 2023/2/26 21:18
 */
@Aspect
@Component
public class MyAnnotationAop {
    @Pointcut("@annotation(com.floweryu.example.annotation.MyAnnotation)")
    public void myAnnotation() {
        
    }
    
    @Before("myAnnotation()")
    public void before() {
        System.out.println("MyAnnotation 开始前");
    }
    
    @AfterReturning(value = "myAnnotation()",returning = "res")
    public Object dochange(JoinPoint joinPoint, Object res){
        System.out.println("AfterReturning 执行前, res: " + res);
        // 获取数据
        Map<String,String> map= (Map<String, String>) res;
        // 添加新值
        map.put("s1","我是在AOP中添加的新值");
        return map;
    }
}
