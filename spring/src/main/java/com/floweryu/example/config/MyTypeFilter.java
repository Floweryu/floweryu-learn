package com.floweryu.example.config;

import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * @author Floweryu
 * @date 2022/2/20 21:17
 */
public class MyTypeFilter implements TypeFilter {
    /**
     * 
     * @param metadataReader 读取到的当前正在扫描的类信息
     * @param metadataReaderFactory 获取其他任何类的信息
     * @return
     * @throws IOException
     */
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        // 获取当前类的注解信息
        AnnotatedTypeMetadata annotatedTypeMetadata = metadataReader.getAnnotationMetadata();
        // 获取当前类的类信息
        ClassMetadata classMetadata = metadataReader.getClassMetadata();
        // 获取当前类的资源信息
        Resource resource = metadataReader.getResource();
        String className = classMetadata.getClassName();
        System.out.println("----class name----: " + className);
        if (className.contains("Service")) {
            return true;
        }
        return false;
    }
}
