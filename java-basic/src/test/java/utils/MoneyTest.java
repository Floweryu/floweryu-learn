package utils;

import bean.Employee;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MoneyTest {
    @Test
    public void calcTest() {
        // 数据库计算的提款金额
        Money sqlAdShare = Money.of(10460.19);
        double divide = sqlAdShare.divide(0.7).divide(0.85).toStoreDecimal().setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        System.out.println("由数据库的值，倒推出的需要修改成的总值: " + divide);
        
        // 原来导出csv计算的总值
        Money csvReal = Money.of(17406.1);
        double minus = csvReal.minus(divide).toStoreDecimal().setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        System.out.println("原来导出csv计算的总值和倒推修正后的差值： " + minus);
    
        Money needFixAdShare = Money.of(divide);
        double real = needFixAdShare.multiply(0.7).multiply(0.85).toStoreDecimal().setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        System.out.println("倒推修改后再计算得到的金额: " + real);
        
        double bookBill = 5898.4886;
        System.out.println("修复后书籍流水： " + (Money.of(bookBill - minus).toStoreDecimal().setScale(4, RoundingMode.HALF_EVEN)));
    }
    
    @Test
    public void thresoldTest() {
        Money thresold = Money.of(100000);
        double thresoldValue = thresold.multiply(0.7).multiply(0.8).toStoreDecimal().setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        // sql中实际付款的值
        Money sqlAdShare = Money.of(882483.20);
        // 减去阈值后剩下的部分
        Money minus = sqlAdShare.minus(thresoldValue);
        Money divide = minus.divide(0.7).divide(0.8);
        Money finalSum = divide.plus(thresold);
        System.out.println("由数据库的值，倒推出的需要修改成的总值: " + finalSum.toStoreDecimal().setScale(2, RoundingMode.HALF_EVEN));
    
        // 原来导出csv计算的总值
        Money csvReal = Money.of(1560260.28);
        BigDecimal bigDecimal = csvReal.minus(finalSum).toStoreDecimal().setScale(2, RoundingMode.HALF_EVEN);
        System.out.println("原来导出csv计算的总值和倒推修正后的差值：" + bigDecimal);
    
        // 先减去10 0000
        Money minus1 = finalSum.minus(thresold);
        Money multiply = minus1.multiply(0.7).multiply(0.8);
        Money refactCal = multiply.plus(thresoldValue);
        System.out.println("重新计算的值： " + refactCal.toStoreDecimal().setScale(2, RoundingMode.HALF_EVEN));
    
        Money bookBill = Money.of(110558.1805);
        double v = bookBill.minus(bigDecimal).toStoreDecimal().setScale(2, RoundingMode.HALF_EVEN).doubleValue();
        System.out.println("修复后书籍流水： " + v);
    }
    
    @Test
    public void moneyTest() {
        Employee employee = new Employee();
        System.out.println(employee.getAge());
        System.out.println(employee.getName());
    }
}
