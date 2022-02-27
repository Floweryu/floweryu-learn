package com.floweryu.example.condition;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author Floweryu
 * @date 2022/2/27 17:42
 */
public class MyImportSelector implements ImportSelector {

    /**
     * 获取导入到容器中的组件全类名
     * @param importingClassMetadata 当前标注@Import注解的类的所有信息
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        // 方法不要返回Null值
        return new String[] {"com.floweryu.example.bean.Blue", "com.floweryu.example.bean.Green"};
    }
}
