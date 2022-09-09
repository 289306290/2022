package com.springbase.vo.importbean;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class TestBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar{

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        //创建一个bean定义
        RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Cup.class);
        //把bean定义对象导入容器
        registry.registerBeanDefinition("cup", rootBeanDefinition);
    }
}
