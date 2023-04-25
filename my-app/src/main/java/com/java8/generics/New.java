package com.java8.generics;

import com.java8.Teacher;

import java.util.*;

public class New {
    public static <K,V> Map<K,V> map(){
        return new HashMap<K, V>();
    }
    public static <T> List<T> list() {
        return new ArrayList<T>();
    }
    public static <T> Set<T> set(){
        return new HashSet<T>();
    }
    public static <T> Queue<T> queue(){
        return new LinkedList<T>();
    }

    public static void f(Map<Teacher, List<? extends  Coffee>> coff){}

    public static void main(String[] args) {
        Map<String,List<String>> sls = New.map();
        List<String> ls = New.list();
        Set<String> ss = New.set();
        Queue<String> qs = New.queue();
        Map<Teacher,List<? extends Coffee>> coffee = New.map(); //类型推断
        f(New.map());
        f(coffee);
        System.out.println("ffff");
    }
}
