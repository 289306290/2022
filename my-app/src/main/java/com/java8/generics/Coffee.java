package com.java8.generics;


import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.Random;
import java.util.Spliterator;
import java.util.function.Consumer;

public class Coffee {
    private static long counter = 0;
    private final long id = counter++;
    public String toString() {
        return getClass().getSimpleName()+" " + id;
    }
}
class Latte extends  Coffee{}
class Mocha extends Coffee {}
class Cappuccino extends Coffee{}
class Americano extends Coffee{}
class Breve extends Coffee{}

class CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private Class[] types = {Latte.class, Mocha.class, Cappuccino.class, Americano.class, Breve.class};
    private static Random random = new Random(47);
    public CoffeeGenerator(){}
    //For iteration
    private int size = 0;

    public CoffeeGenerator(int sz){size= sz;}
    public Coffee next(){
        try {
            int r= random.nextInt(types.length);
//            System.out.println("随机数=====" + r);
            return (Coffee) types[r].newInstance();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }



    @Override
    public void forEach(Consumer<? super Coffee> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator<Coffee> spliterator() {
        return Iterable.super.spliterator();
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;
        public boolean hasNext(){ return count > 0;}
        public Coffee next(){
            count--;
            return CoffeeGenerator.this.next();
        }

        public void remove() { // Not implemented
            throw new UnsupportedOperationException();
        }
    };
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) {
        CoffeeGenerator gen = new CoffeeGenerator();
        for (int i = 0; i < 5; i++) {
            System.out.println("next---->"+gen.next());
        }
        for (Coffee c : new CoffeeGenerator(5)) {
            System.out.println(c);
        }

    }
}
