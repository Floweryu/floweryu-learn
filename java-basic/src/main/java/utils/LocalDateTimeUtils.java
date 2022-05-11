package utils;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

/**
 * @author zhangjunfeng
 * @date 2022/5/10 11:02
 */
public class LocalDateTimeUtils {
    public static void main(String[] args) {
        // 获取一个月的第一天日期
        LocalDate start = LocalDate.of(2022, 5, 1);
        System.out.println(start);
        // 获取一个月的最后一天日期
        LocalDate end = LocalDate.of(2022, 5, 1).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(end);
    }
}
