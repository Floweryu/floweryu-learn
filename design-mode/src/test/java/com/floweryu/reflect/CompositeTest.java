package com.floweryu.reflect;

import com.floweryu.composite.Department;
import com.floweryu.composite.FinancialDepartment;
import com.floweryu.composite.HeadDepartment;
import com.floweryu.composite.SalesDepartment;
import org.junit.Test;

/**
 * @author Floweryu
 * @date 2021/11/28 20:26
 */
public class CompositeTest {
    @Test
    public void testComposite() {
        Department salesDepartment = new SalesDepartment(
                1, "Sales department");
        Department financialDepartment = new FinancialDepartment(
                2, "Financial department");

        HeadDepartment headDepartment = new HeadDepartment(
                3, "Head department");

        headDepartment.addDepartment(salesDepartment);
        headDepartment.addDepartment(financialDepartment);

        headDepartment.printDepartmentName();
    }
}
