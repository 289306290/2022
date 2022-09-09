package com.example.memoryleak;

import com.example.People;

import java.util.Vector;

public class TestMemoryLeak {

    /**
     * 有引用的对象，但是已经不再使用了，就会产生内存泄漏
     * @param args
     */
    public static void main(String[] args) {
        Vector<Object> v= new Vector<>();
        for(int i=0; i < 100; i++) {
            People o = new People("张三"+i ,10);
            v.add(o);
            o = null;
        }
        System.out.println("over");
    }

}
