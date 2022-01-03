package com.floweryu.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Floweryu
 * @date 2021/12/30 23:57
 */
@SpringBootApplication
public class FloweryuApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =  SpringApplication.run(FloweryuApplication.class);
//        System.out.println(context.getBeanDefinitionNames().length);
//        for (String name : context.getBeanDefinitionNames()) {
//            System.out.println(name);
//        }
//
//        User user1 = context.getBean("Dogger", User.class);
//        User user2 = context.getBean("Dogger", User.class);
//        System.out.println("siglon: " + (user1 == user2));
//
//        MyConfig myConfig = context.getBean(MyConfig.class);
//        System.out.println(myConfig);
//        
//        User user3 = myConfig.user1();
//        User user4 = myConfig.user1();
//        System.out.println("proxyBeanMethods check: " + (user3 == user4));
//        // 如果proxyBeanMethods为true，则输出true
//        // 如果proxyBeanMethods为false，则输出false
//        
//        User user5 = context.getBean("Dogger", User.class);
//        Pet pet1 = context.getBean("Tom", Pet.class);
//        System.out.println("User's pet is equal pet?? " + (user5.getPet() == pet1));
//        // 如果proxyBeanMethods为true，则输出true
//        // 如果proxyBeanMethods为false，则输出false
//
//        String[] beanNamesForType = context.getBeanNamesForType(User.class);
//        for (String s : beanNamesForType) {
//            System.out.println(s);
//        }
        boolean b = context.containsBean("Tom");
        System.out.println("is Tom exist : " + b);

        boolean b1 = context.containsBean("Dogger");
        System.out.println("is Dogger exist : " + b1);
    }
}
