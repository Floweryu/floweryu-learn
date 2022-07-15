package utils;

import org.junit.Test;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author zhangjunfeng
 * @date 2022/5/24 10:49
 */
public class BigDecimalUtilTest {
    @Test
    public void divCheckNoScaleTest() {
        double a = 10742.61;
        double b = 42152.65;
        System.out.println(BigDecimalUtil.divCheckNoScale(a, b));
    }
    
    @Test
    public void mulTest() {
        double origin = 7967.23445;
        double income = 70108.7338;
        double p1 = 0.22782275229083743;
        double p2 = 0.07992950120609696;
        System.out.println(1 - p1 - p2);
        double p3 = 0.69;
        System.out.println(0.1624478970424559 + 0.1096587953749356 + 0.7278933075826085);
        double a =  10705.322016 + 7226.518396 + 47968.194066;
        System.out.println("ssss: " + a);
        
        System.out.println(BigDecimalUtil.mul(origin, p1));
        System.out.println(BigDecimalUtil.mul(origin, p2));
        System.out.println(BigDecimalUtil.mul(origin, p3));
        System.out.println(BigDecimalUtil.mul(origin, p1) + BigDecimalUtil.mul(origin, p2) + BigDecimalUtil.mul(origin, p3));
        System.out.println("------------------------------");
        System.out.println(BigDecimalUtil.mul(income, p1));
        System.out.println(BigDecimalUtil.mul(income, p2));
        System.out.println(BigDecimalUtil.mul(income, p3));
        System.out.println(BigDecimalUtil.mul(income, p1) + BigDecimalUtil.mul(income, p2) + BigDecimalUtil.mul(income, p3));
    }
    
    @Test
    public void roundHalfUpTest() {
        double a1 = 5371.31;
        double b1 = 2547.29;
        double c1 = 13157.74;
        
        double sum = a1 + b1 + c1;
        System.out.println(sum);
        double adShare = sum * 0.7 * 0.85;
        System.out.println(adShare);
        
        double percent1 = BigDecimalUtil.divCheckNoScale(a1, sum);
        double percent2 = BigDecimalUtil.divCheckNoScale(b1, sum);
        double percent3 = BigDecimalUtil.divCheckNoScale(c1, sum);
        System.out.println(percent1);
        System.out.println(percent2);
        System.out.println(percent3);
        System.out.println(percent1 + percent2 + percent3);

        double adShareOrigin1 = BigDecimalUtil.mul(adShare, percent1);
        double adShareOrigin2 = BigDecimalUtil.mul(adShare, percent2);
        double adShareOrigin3 = BigDecimalUtil.mul(adShare, percent3);
        System.out.println(adShareOrigin1);
        System.out.println(adShareOrigin2);
        System.out.println(adShareOrigin3);
        System.out.println((adShareOrigin1 + adShareOrigin2 + adShareOrigin3));
        System.out.println((adShareOrigin3) * 0.5);

    }
    
    @Test
    public void divTest() {
        double percent1 = BigDecimalUtil.divCheckNoScale(10742.61, 42152.65);
        double percent2 = BigDecimalUtil.divCheckNoScale(5094.57, 42152.65);
        double percent3 = BigDecimalUtil.divCheckNoScale(26315.47, 42152.65);
        double a1 = BigDecimalUtil.mul(25080.82675, percent3);
        double b1 = BigDecimalUtil.mul(25080.82675, percent2);
        double c1 = BigDecimalUtil.mul(25080.82675, percent1);
        System.out.println(BigDecimalUtil.mul(25080.82675, percent1));
        System.out.println(BigDecimalUtil.mul(25080.82675, percent2));
        System.out.println(BigDecimalUtil.mul(25080.82675, percent3));
        System.out.println((a1 + b1 + c1) * 0.5);

        System.out.println(10742.61 + 5094.57 + 26315.47);
        System.out.println(11487.52 * 0.7 * 0.85);
    }
    
    @Test
    public void roundModeTest() {
        double incomeDayAdjusted = BigDecimalUtil.mul(46199.39, 0.8);
        System.out.println(incomeDayAdjusted);
        double shareDayAdjusted = incomeDayAdjusted * 0.7 * 0.8;
        System.out.println(shareDayAdjusted);
        
        double shareMonthAdjusted = BigDecimalUtil.add(42606.7, shareDayAdjusted);
        System.out.println(shareMonthAdjusted);
        String today = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(today);
        String yes = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        System.out.println(yes);
    }
    
    @Test
    public void dailyCalc() throws ParseException {
        // 日流水
    }
    
}
