package bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

/**
 * @author zhangjunfeng
 * @date 2022/3/7 15:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private String name;
    private int age;
    private double salary;
    private long mobile;
}
