package com.floweryu.example.config;

import com.floweryu.example.bean.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author Floweryu
 * @date 2022/3/6 21:26
 */
public class PropertyConfigTest {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfigOfProperty.class);

    @Test
    public void propertyTest() {
        printNames();

        System.out.println("----------------");
        Person person = (Person) context.getBean("person");
        System.out.println(person);

        ConfigurableEnvironment environment = context.getEnvironment();
        String property = environment.getProperty("person.nickName");
        System.out.println(property);
    }


    private void printNames() {
        String[] names = context.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
    }
}
