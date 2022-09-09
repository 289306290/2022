package com.springbase.vo;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class Student {

    @Value("${stu.age}")
    private int age;

    public Student(int age){
        this.age = age;
        System.out.println("构造方法 Student");
    }

    @PostConstruct
    public void init(){
        System.out.println("这里是Student的 @PostConstruct  方法");
    }

    @PreDestroy
    public void destory(){
        System.out.println("这里是Student的destory方法");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
