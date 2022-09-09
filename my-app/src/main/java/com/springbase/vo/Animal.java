package com.springbase.vo;

import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.util.StringValueResolver;

public class Animal implements EmbeddedValueResolverAware{

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Animal(){
        System.out.println("构造方法 Animal");
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.name = resolver.resolveStringValue("${animal.name}");
    }
}
