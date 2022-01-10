package com.floweryu.example.bean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Floweryu
 * @date 2021/12/28 0:00
 */
public class BeanFactoryTest {
    @Test
    public void testBeanLoad() {
        ApplicationContext bf = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
        MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }
    
    @Test
    public void testBook() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beanFactoryTest.xml");
        Book book = (Book) context.getBean("book");
        System.out.println(book);
    }
}
