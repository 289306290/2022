package com.designpatterns.observe.spring;

import org.springframework.context.ApplicationEvent;


/**
 * 定义另一个事件,监听对象 监听固定的事件,对其他事件不监听
 */
public class JavaAnotherEvent extends ApplicationEvent {
    public JavaAnotherEvent(Object source) {
        super(source);
    }
}
