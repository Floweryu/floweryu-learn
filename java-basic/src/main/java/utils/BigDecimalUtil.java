package utils;

import java.math.BigDecimal;

/**
 * @author zhangjunfeng
 * @date 2022/5/24 10:47
 */
public class BigDecimalUtil {
    private static int scale = 2;
    private static int scale4 = 4;
    private static int roundingMode = BigDecimal.ROUND_HALF_UP;
    private static int roundingDownMode = BigDecimal.ROUND_DOWN;

    /**
     * 四舍五入，小数点后保留两位
     *
     * @param value
     * @return
     */
    public static Double roundHalfUp(Double value) {
        return BigDecimal.valueOf(value).setScale(scale, roundingMode).doubleValue();
    }

    public static Double roundHalfUpScale4(Double value) {
        return BigDecimal.valueOf(value).setScale(scale4, roundingMode).doubleValue();
    }

    public static Double roundHalfUp(Double value, int pScale) {
        return BigDecimal.valueOf(value).setScale(pScale, roundingMode).doubleValue();
    }

    public static Double roundDown(Double value, int pScale) {
        return BigDecimal.valueOf(value).setScale(pScale, roundingDownMode).doubleValue();
    }

    public static double add(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.add(b2).setScale(scale, roundingMode).doubleValue();
    }

    public static double addScale4(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.add(b2).setScale(scale4, roundingMode).doubleValue();
    }

    public static double sub(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.subtract(b2).setScale(scale, roundingMode).doubleValue();
    }

    public static double subScale4(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.subtract(b2).setScale(scale4, roundingMode).doubleValue();
    }

    public static double mul(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.multiply(b2).setScale(scale, roundingMode).doubleValue();
    }

    public static double mulScale4(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.multiply(b2).setScale(scale4, roundingMode).doubleValue();
    }

    public static double div(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.divide(b2, scale, roundingMode).doubleValue();
    }

    public static double divScale4(double d1, double d2) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.divide(b2, scale4, roundingMode).doubleValue();
    }

    public static double div(double d1, double d2, int pScale) {
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.divide(b2, pScale, roundingMode).doubleValue();
    }

    public static double divCheckNoScale(double d1, double d2) {
        if (Double.compare(0d, d2) == 0) {
            if (Double.compare(0d, d1) == 0) {
                System.out.println("divCheckNoScale {}, {}: " + d1 + " ,  " + d2);
            }
            return 0d;
        }
        BigDecimal b1 = BigDecimal.valueOf(d1);
        BigDecimal b2 = BigDecimal.valueOf(d2);
        return b1.divide(b2, roundingMode).doubleValue();
    }


    /**
     * 精度舍入问题验证 https://blog.csdn.net/b644ROfP20z37485O35M/article/details/105570727
     * @param args
     */
    public static void main(String[] args) {
        double a =1.01;
//        double b =2.023;// 舍入一样
        double b =2.025;// 舍入不同
//        double b =2.029;// 舍入一样
        System.out.println(a + b);
        System.out.println(BigDecimalUtil.add(a, b));
        System.out.println(BigDecimalUtil.add2(a, b));
        System.out.println(BigDecimalUtil.add(a, b) - BigDecimalUtil.add2(a, b));
    }

    /**
     * test
     * @deprecated 小数点第三位是5时，有精度舍入问题：1.01+2.025=3.03（期望是3.04）
     * @see BigDecimalUtil#add(double, double)
     */
    private static double add2(double d1, double d2) {
        BigDecimal b1 = new BigDecimal(d1);
        BigDecimal b2 = new BigDecimal(d2);
        return b1.add(b2).setScale(scale, roundingMode).doubleValue();
    }
}
