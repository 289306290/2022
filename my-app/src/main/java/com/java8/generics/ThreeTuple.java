package com.java8.generics;

public class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
    public final C thrid;
    public ThreeTuple(A first, B second,C third) {
        super(first, second);
        this.thrid = third;
    }
}
