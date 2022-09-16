package com.designpatterns.observe.normal;

public class Test {

    public static void main(String[] args) {
        GoldPriceObservable goldPriceObservable = new GoldPriceObservable("黄金戒指", 3032.2);
        GoldPriceObserver observer1 = new GoldPriceObserver("厂家");
        GoldPriceObserver observer2 = new GoldPriceObserver("消费者");
        goldPriceObservable.addListener(observer1);
        goldPriceObservable.addListener(observer2);


        goldPriceObservable.setProductPrice(2080d);

        System.out.println("============================================");
        goldPriceObservable.removeListener(observer1);

        goldPriceObservable.setProductPrice(2d);

    }
}
