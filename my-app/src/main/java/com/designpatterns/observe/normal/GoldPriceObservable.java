package com.designpatterns.observe.normal;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 被观察者
 */
public class GoldPriceObservable {

    private String productName;

    private Double productPrice;

    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    public GoldPriceObservable(String productName, Double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        String oldVal = String.valueOf(this.productPrice);
        this.productPrice = productPrice;
        String newVal = String.valueOf(this.productPrice);
        //发布监听事件
        fireProperty(productName, oldVal, newVal);
    }


    //添加观察者对象
    public void addListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    //移除观察者对象
    public void removeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

    public void fireProperty(String prop, Object oldVal, Object newVal) {
        changeSupport.firePropertyChange(prop, oldVal, newVal);
    }
}
