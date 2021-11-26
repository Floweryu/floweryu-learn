package com.floweryu.reflect.statics;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author zhangjunfeng
 * @date 2021/11/25 20:36
 */
public class StaticUtility {
    public static String getAuthorName() {
        return "Umang Budhwar";
    }

    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    public static LocalTime getLocalTime() {
        return LocalTime.now();
    }
}
