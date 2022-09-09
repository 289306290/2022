package com.springbase.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyCondition implements Condition{
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        if (context.getBeanFactory().containsBean("animal")) {
            //这里只要有animal的beanDefination就会是包含,不一定要实例化之后的animal
            System.out.println("------------ animal 存在");
            return true;
        }
        return false;
    }
}
