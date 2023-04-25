package com.java8.generics;

public class GenericMethod {
    public <T> void f(T x) {
        System.out.println(x.getClass().getName());
    }

    public <T,V,E> void f(T x,V v,E e) {
        System.out.println(x.getClass().getName()+"," + v.getClass().getName() + ", " + e.getClass().getName());
    }

    public <T,V,E> void f(T x,V v,String e) {
        System.out.println(x.getClass().getName()+"     " + v.getClass().getName() + "    " + e.getClass().getName());
    }

    public static void main(String[] args) {
        GenericMethod gm = new GenericMethod();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);

        gm.f(1, "", 3.0);
        gm.f(1, "", "");
    }

}
