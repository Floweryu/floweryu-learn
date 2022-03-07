package utils;

import bean.Employee;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhangjunfeng
 * @date 2022/3/7 15:46
 */
public class CompareTest {
    @Test
    public void sortTest() {
        List<Employee> collect = CompareUtils.list.stream().sorted(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int res = o1.getAge() - o2.getAge();
                System.out.println("res: " + res);
                return res;
            }
        }).collect(Collectors.toList());
        collect.forEach(System.out::println);
    }
}
