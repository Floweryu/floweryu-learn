package com.floweryu.example.bean;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @author Floweryu
 * @date 2021/12/28 0:00
 */
public class BeanFactoryTest {
    @Test
    public void testBeanLoad() {
        BeanFactory bf = new XmlBeanFactory(new ClassPathResource("beanFactoryTest.xml"));
        MyTestBean bean = (MyTestBean) bf.getBean("myTestBean");
        System.out.println(bean.getTestStr());
    }
}
