package stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangjunfeng
 * @date 2021/12/2 17:48
 */
public class StreamTest {
    @Test
    public void testListToMap() {
        List<Student> list = Arrays.asList(new Student(1, 18, "阿龙"),
                new Student(2, 17, "小花"),
                new Student(3, 17, "阿浪"));
        // value 为对象 student -> student jdk1.8返回当前对象
        Map<Integer, Student> map = list.stream().collect(Collectors.toMap(Student::getId, student -> student));
        // 遍历打印结果
        map.forEach((key, value) -> {
            System.out.println("key: " + key + "    value: " + value);
        });
    }
    
    @Test
    public void testIntegerMax() {
        int a = Integer.MAX_VALUE;
        System.out.println(a);
        System.out.println(a + 1);
    }
}
