package utils;

import bean.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangjunfeng
 * @date 2022/3/7 15:37
 */
public class CompareUtils {


    public static List<Employee> list = new ArrayList<>();
    
    static {
        Employee employee1 = new Employee("Keith", 35, 4000.0, 3924401);
        Employee employee2 = new Employee("John", 25, 3000.0, 9922001);
        Employee employee3 = new Employee("Ace", 22, 2000.0, 5924001);
        list.add(employee1);
        list.add(employee2);
        list.add(employee3);
    }
}
