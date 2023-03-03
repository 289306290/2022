package com.example.algorithm.zuochengyun.doublelist;



public class ReverseDoubleLinkList {
    public static void main(String[] args) {
        DoubleList<Integer> doubleList = new DoubleList();
        doubleList.addFirst(3);
        doubleList.addFirst(4);
//        doubleList.addFirst(5);
//        doubleList.addFirst(6);
//        doubleList.addFirst(7);

        System.out.println(doubleList.toString());
        doubleList.reverse();
        System.out.println(doubleList.toString());
    }
}
