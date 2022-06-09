package utils;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
}
