package com.java8.generics;

public class TwoTuple<A,B> {
    public  final A first;
    public final B second;

    public TwoTuple(A first, B second) {
        this.first = first;
        this.second = second;
    }
}
