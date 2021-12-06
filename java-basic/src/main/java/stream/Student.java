package stream;

import lombok.Data;

/**
 * @author zhangjunfeng
 * @date 2021/12/2 17:47
 */
@Data
public class Student {
    private Integer id;
    
    private Integer age;
    
    private String name;
    
    public Student(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }
}
