package com.java8.generics;

import java.util.*;

public class TestGenerics {

    static class A{
        public static void add(Map<String, ? extends A> add){}

        public static void minius(List<?> a) {

        }

        static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
            for (T o : a) {
                c.add(o);
            }
        }
    }
    static class B extends A{}

    public static void main(String[] args) {
        List<A> aList = new ArrayList<>();
        List<B> bList = new ArrayList<>();
//        aList = bList; //报错
//        bList = aList; //报错
        List<Object> objectList = new ArrayList<>();
        /**
         *List<Object> 并不是 List<A> 或者List<B>的父类,他们之间不能相互赋值,例如
         * objectList = aList;//也会报错
         */
//        objectList = aList;//也会报错

        //上面这个问题,可以用通配符来解决 ?
        List<?> list = new ArrayList<>();
        list = aList;
//        list.add(new A());//但是现在不能添加元素,因为不知道list的类型
        List<? extends A> extendA = new ArrayList<>();
        A a = new A();
//        extendA.add(a);//这会编译错误,但是下面这个可行。
        A.minius(aList);
        Map<String, B> mapB = new HashMap<>();
        A.add(mapB);
    }
}
