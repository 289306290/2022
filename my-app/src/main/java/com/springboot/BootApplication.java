package com.springboot;

import com.springboot.banner.MyBanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@SpringBootApplication
public class BootApplication {

    private static ConfigurableApplicationContext ctx;


    public static void main(String[] args) {
//        ctx = SpringApplication.run(BootApplication.class);
        SpringApplication application = new SpringApplication(BootApplication.class);
        application.setBanner(new MyBanner());
        application.setApplicationStartup(new BufferingApplicationStartup(2048));
        application.run(args);




    }
}
