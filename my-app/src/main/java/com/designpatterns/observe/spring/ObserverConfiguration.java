package com.designpatterns.observe.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置监听
 */
@Configuration
public class ObserverConfiguration {

   /* @Bean
//    @DependsOn(value = {"readListener1"})
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return (args -> {
            System.out.println("这里commandLineRunner发布一个事件:什么是观察者?");
            PersonVo p = new PersonVo();
            p.setName("这里commandLineRunner发布一个事件:什么是观察者?");
            context.publishEvent(new JavaEvent(p));
        });
    }*/

    @Bean
    public ReadListener readListener1() {
        return new ReadListener("小明");
    }

    /*@Bean
    public ReadListener readListener2() {
        return new ReadListener("小红");
    }

    @Bean
    public ReadListener readListener3() {
        return new ReadListener("小白");
    }*/
}
