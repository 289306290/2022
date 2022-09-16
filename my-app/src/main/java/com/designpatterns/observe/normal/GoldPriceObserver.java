package com.designpatterns.observe.normal;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * 观察者
 */
public class GoldPriceObserver implements PropertyChangeListener{

    private String name;


    public GoldPriceObserver(String name) {
        this.name = name;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String propertyName = evt.getPropertyName();
        String oldPrice = (String) evt.getOldValue();
        String newPrice = (String) evt.getNewValue();
        System.out.println("观察者收到:" + this.name + "," + propertyName + "的价格"
         + "从原来的 "+ oldPrice + ", 变为 " + newPrice);

    }
}
