package com.floweryu.example.annotation;

import java.lang.annotation.*;

/**
 * @author Floweryu
 * @date 2023/2/26 21:16
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAnnotation {
    
}
