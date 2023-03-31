package com.test1.duotai;

public class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("这里是Dog类的eat方法");
    }

    public void walk() {
        System.out.println("这是Dog类独有的方法walk!");
    }
}
