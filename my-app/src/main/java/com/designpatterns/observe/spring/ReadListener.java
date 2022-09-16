package com.designpatterns.observe.spring;

import com.springbase.vo.PersonVo;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;

public class ReadListener implements ApplicationListener<JavaEvent> {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ReadListener(String name) {
        this.name = name;
    }

    @Override
    @Async
    public void onApplicationEvent(JavaEvent event) {
        updateInfo(event);
    }

    private void updateInfo(JavaEvent event) {
        PersonVo personVo = (PersonVo) event.getSource();
        System.out.println("线程: "+Thread.currentThread().getName() +",这里获取到事件了:" + personVo.toString());
    }
}
