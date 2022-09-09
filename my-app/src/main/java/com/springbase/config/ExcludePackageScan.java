package com.springbase.config;

import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

import java.io.IOException;

/**
 * 自定义包扫描的匹配类型规则
 */
public class ExcludePackageScan implements TypeFilter{
    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
        if (annotationMetadata.isAnnotated("com.springbase.config.Encrypt")) {
            //如果包含了Encrypt这个注解,则匹配到
            System.out.println("==========================匹配了注解");
            return true;
        }
        return false;
    }
}
