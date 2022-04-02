package com.floweryu.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * @author Floweryu
 * @date 2022/3/13 15:37
 */
@Aspect
public class LogAspects {

    /**
     * 1. 本类引用，只需要写方法名
     * 2. 其他类引用，需要写路径
     */
    @Pointcut("execution(public int com.floweryu.example.aop.MathCalculator.*(..))")
    public void pointCut() {}

    /**
     * 前置通知：在目标方法被调用之前调用通知功能
     */
    @Before("pointCut()")
    public void logStart(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        System.out.println(joinPoint.getSignature().getName() + " 运行开始.......参数列表是: {" + Arrays.toString(args) + "}");
    }

    /**
     * 后置通知：在目标方法调用之后调用通知，此时不关心方法的输出结果
     */
    @After("pointCut()")
    public void logEnd() {
        System.out.println("除法结束.......");
    }

    /**
     * 返回通知：在目标方法执行成功后调用通知
     */
    @AfterReturning(value = "pointCut()", returning = "returning")
    public void logReturn(Object returning) {
        System.out.println("除法正常返回.......返回值: {" + returning + "}");
    }

    /**
     * 异常通知：在目标方法抛出异常后调用通知
     */
    @AfterThrowing(value = "pointCut()", throwing = "ex")
    public void logException(Exception ex) {
        System.out.println("除法异常.......异常: {" + ex +"}");
    }
    
    @Around("pointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("方法执行前调用========");
        Object result = joinPoint.proceed();
        System.out.println("方法执行后调用========");
        return result;
    }
}
