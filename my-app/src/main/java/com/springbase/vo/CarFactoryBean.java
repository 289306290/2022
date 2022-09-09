package com.springbase.vo;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CarFactoryBean implements FactoryBean<Car>{
    @Nullable
    @Override
    public Car getObject() throws Exception {
        return new Car();
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
