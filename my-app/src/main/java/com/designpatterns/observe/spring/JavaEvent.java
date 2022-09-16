package com.designpatterns.observe.spring;

import org.springframework.context.ApplicationEvent;

/**
 * 观察目标类,事件类
 */
public class JavaEvent extends ApplicationEvent{

    public JavaEvent(Object source) {
        super(source);
    }

}
