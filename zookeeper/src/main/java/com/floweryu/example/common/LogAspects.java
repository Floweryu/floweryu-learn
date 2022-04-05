package com.floweryu.example.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author Floweryu
 * @date 2022/4/5 20:12
 */
@Slf4j
public class LogAspects {

    @Pointcut("execution(* com.floweryu.example.cache.LocalCacheUtil.*(..))")
    public void pointCut() {}

    @After("pointCut()")
    public void logEnd(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        log.info("方法 [" + signature.getName() + "] 执行后, cacheMap={}");
    }
}
