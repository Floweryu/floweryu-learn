package utils;

import org.junit.Test;

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
        double a = 25080.82675;
        double b = 0.01;
        System.out.println(BigDecimalUtil.mul(a, b));
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
}
