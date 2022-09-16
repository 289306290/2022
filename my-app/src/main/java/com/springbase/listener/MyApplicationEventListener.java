package com.springbase.listener;

import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;


/**
 * 设置异步执行器，监听器处理设置异步处理
 * 源码相关在this.initApplicationEventMulticaster();里面
 */
@Component("applicationEventMulticaster")
public class MyApplicationEventListener extends SimpleApplicationEventMulticaster {

    public MyApplicationEventListener() {
        setTaskExecutor(Executors.newSingleThreadExecutor());
    }
}
