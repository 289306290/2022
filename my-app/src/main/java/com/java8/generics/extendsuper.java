package com.java8.generics;

import java.util.ArrayList;
import java.util.List;

public class extendsuper {
    /**
     * 生物
     */
    static class Biological{
    }
    /**
     * 动物
     */
    static class Animal extends Biological{
    }
    /**
     * 植物
     */
    static class Plant extends Biological{
    }

    static class Dog extends Animal{
    }
    static class Cat extends Animal{
    }
    static class Flower extends Plant{
    }
    static class Tree extends Plant{
    }

    public static void main(String[] args) {
        List<? super Animal> listA = new ArrayList<>();
        List<? extends Plant> listB = new ArrayList<>();
        //listA.add(new Biological());
        listA.add(new Animal());
        listA.add(new Dog());
        //listA.add(new Flower());
        Object object = listA.get(0);
        //listB.add(new Plant());
        listB.add(null);
        Plant plant = listB.get(0);
        //-------上述 <？ extend Plant> 虽然不能往里面放值,但是可以直接用来接收另一个方法返回的一个list
        List<? extends Plant> listPlant = listTree();
        listPlant.forEach(t-> System.out.println(t.toString()));
        //---------------------
        Dog dog = (Dog) listA.get(1);
        System.out.println(dog);
    }

    public static List<Tree> listTree() {
        List<Tree> list = new ArrayList<>();
        list.add(new Tree());
        list.add(new Tree());
        return list;
    }

}
