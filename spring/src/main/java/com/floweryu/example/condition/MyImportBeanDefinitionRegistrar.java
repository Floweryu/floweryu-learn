package com.floweryu.example.condition;

import com.floweryu.example.bean.RainBow;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Floweryu
 * @date 2022/2/27 20:47
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 
     * @param importingClassMetadata 当前类的注解信息
     * @param registry：BeanDefinition注册类
     *                把所有需要添加到容器中的Bean，
     *                使用BeanDefinitionRegistry.registerBeanDefinition手动注入
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        boolean red = registry.containsBeanDefinition("com.floweryu.example.bean.Red");
        boolean blue = registry.containsBeanDefinition("com.floweryu.example.bean.Blue");
        // 如果容器中有上面的bean，则注入rainBow
        if (red && blue) {
            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(RainBow.class);
            // 注入一个bean，并且指定名字
            registry.registerBeanDefinition("rainBow", rootBeanDefinition);
        }
    }
}
