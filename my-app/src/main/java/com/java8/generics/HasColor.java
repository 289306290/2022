package com.java8.generics;

import java.awt.*;

public interface HasColor {
    Color getColor();
}
class Colored<T extends HasColor> {
    T item;

    Colored(T item) {
        this.item = item;
    }
    T getItem() {
        return item;
    }
    Color color(){
        return item.getColor();
    }
}
class Dimension{
    public int x,y,z;
}

class ColoredDimension<T extends Dimension & HasColor> {
    T item;

    ColoredDimension(T item) {
        this.item = item;
    }
    T getItem(){
        return item;
    }
    Color color(){
        return item.getColor();
    }
    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}
}

interface Weight { int weight();}
class Solid<T extends Dimension & HasColor & Weight> {
    T item;

    Solid(T item) {
        this.item = item;
    }
    Color color(){return item.getColor();}
    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}
    int weight(){return item.weight();}
}

class Bounded extends Dimension implements HasColor, Weight{
    public Color getColor(){return null;}
    public int weight(){return 0;}
}

class BasicBounds{
    public static void main(String[] args) {
        Solid<Bounded> solid = new Solid<>(new Bounded());
        solid.color();
        solid.getX();
        solid.weight();
    }
}

class HoldItem<T>{
    T item;
    HoldItem(T item){this.item = item;}
    T getItem(){return item;}
}

class Colored2<T extends HasColor> extends HoldItem<T>{

    Colored2(T item) {
        super(item);
    }
    Color color(){return item.getColor();}
}
class ColoredDimension2<T extends Dimension & HasColor> extends Colored2<T>{

    ColoredDimension2(T item) {
        super(item);
    }
    int getX(){return item.x;}
    int getY(){return item.y;}
    int getZ(){return item.z;}
}
class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T>{
    Solid2(T item){
        super(item);
    }
    int weight(){
        return item.weight();
    }
}
 class InheritBounds{
     public static void main(String[] args) {
         Solid2<Bounded> solid2 = new Solid2<>(new Bounded());
         solid2.color();
         solid2.getY();
         solid2.weight();
     }
}

