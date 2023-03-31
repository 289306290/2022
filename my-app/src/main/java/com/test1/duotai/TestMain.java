package com.test1.duotai;

public class TestMain {
    public static void main(String[] args) {
        Animal dog = new Dog();
        dog.eat();
        Dog dog1 = (Dog) dog;
        dog1.eat();
        dog1.walk();

        Animal animal =new Animal();
        animal.eat();

        Bird bird = (Bird) dog; //这么转会报错 com.test1.duotai.Dog cannot be cast to com.test1.duotai.Bird
        bird.eat();


    }
}
