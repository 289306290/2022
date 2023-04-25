package com.java8.generics;

import java.util.Iterator;

public class Fibonacci implements Generator<Integer>{
    private int count = 0;
    @Override
    public Integer next() {
        return fib(count++);
    }

    private Integer fib(int i) {
        if (i < 2) {
            return 1;
        }
        return fib(i - 2) + fib(i - 1);
    }

    public static void main(String[] args) {
        Fibonacci gen = new Fibonacci();
        for (int i = 0; i < 18; i++) {
            System.out.print(gen.next()+" ");
        }
    }
}

class IterableFibonacci extends Fibonacci implements Iterable<Integer> {
    private int n;
    public IterableFibonacci(int count){ n = count;}
    public Iterator<Integer> iterator(){
        return new Iterator<Integer>() {
            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Integer next() {
                n--;
                return IterableFibonacci.this.next();
            }
        };
    }

    public static void main(String[] args) {
        for (int i : new IterableFibonacci(18)) {
            System.out.print(i+" ");
        }
    }
}
