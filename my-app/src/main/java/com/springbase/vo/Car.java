package com.springbase.vo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Car implements InitializingBean, DisposableBean{

    @PostConstruct
    public void init() {
        System.out.println("car @PostConstruct");
    }

    @PreDestroy
    public void destory(){
        System.out.println("car @PreDestroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("car  实现了InitializingBean的 afterPropertiesSet()方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("car  实现了DisposableBean的 destroy()方法");
    }
}
