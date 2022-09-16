package com.designpatterns.observe.spring;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 监听
 */
@Component
public class MyEventListener {

    /**
     * classes指定监听哪些事件
     * @param object
     */
    @EventListener(classes = {JavaEvent.class,JavaAnotherEvent.class})
    @Async
    public void handlerMsg(Object object) {
        //如果@EventListener中classes只有JavaEvent.class的话,这里参数Object object可以改为JavaEvent javaEvent
        System.out.println("线程: "+Thread.currentThread().getName() +",注解配置@EventListener获取到事件:" + object);
    }
}
