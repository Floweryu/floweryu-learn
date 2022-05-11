package utils;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author zhangjunfeng
 * @date 2022/5/11 15:02
 */
public class DecimalUtilTest {

    public static double mulScale4(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.multiply(b2).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    @Test
    public void mainTest() {
        System.out.println(mulScale4(1, 3.33333));
    }
}
