package com.designpatterns.observe.spring;

import com.springbase.vo.PersonVo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ApplicationEvent {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ApplicationEvent.class);

        PersonVo personVo = new PersonVo();
        personVo.setName("李四");
        personVo.setAge(18);
        ctx.publishEvent(new JavaEvent(personVo));

        ctx.publishEvent(new JavaAnotherEvent("nice"));
    }
}
