package com.springbase.vo;

import org.springframework.beans.factory.annotation.Value;

public class PersonVo {

    public PersonVo(){
        System.out.println("构造方法PersonVo");
    }
    @Value("${person.name}")
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
