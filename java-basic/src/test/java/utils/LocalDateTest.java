package utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Optional;

public class LocalDateTest {
    @Test
    public void test() {
        int year = LocalDate.now().getYear();
        System.out.println(LocalDate.now().getMonthValue());
        LocalDate of = LocalDate.parse("2022-04-05");
        System.out.println(of.minusMonths(1).getMonthValue());
    
        System.out.println(LocalDateTime.of(1,1,1,0,0,0));
        System.out.println(LocalDate.of(2022, 6, 1));
    }

    @Test
    public void optional() {
        String test = null;
        Optional<Boolean> u = Optional.ofNullable(test).map(Boolean::parseBoolean);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate preMonthLastDay = LocalDate.now().minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
        String format = df.format(preMonthLastDay);
        System.out.println(format);

        LocalDate of = LocalDate.of(2023, 1, 1);
        LocalDate now = of.minusMonths(1);
        int year = now.getYear();
        int month = now.getMonthValue();
        System.out.println(year);
        System.out.println(month);
    }
}
