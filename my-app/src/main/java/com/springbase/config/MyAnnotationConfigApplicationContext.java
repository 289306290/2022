package com.springbase.config;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyAnnotationConfigApplicationContext extends AnnotationConfigApplicationContext {
    @Override
    protected void initPropertySources() {
        //设置应用启动时候必须有requiredParam参数,如果没有则应用无法启动
        getEnvironment().setRequiredProperties("requiredParam");
    }

    public MyAnnotationConfigApplicationContext(Class<?> annotatedClass) {
        super(annotatedClass);
    }


}
