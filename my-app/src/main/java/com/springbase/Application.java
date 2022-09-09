package com.springbase;

import com.springbase.config.MainConfig;
import com.springbase.context.SpringContextHolder;
import com.springbase.vo.Student;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext configApplicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
//        System.out.println(configApplicationContext.getBean("personVo"));
        String[] beanNames = configApplicationContext.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(name -> System.out.println(configApplicationContext.getBean(name)));

        Student student = SpringContextHolder.getBean("student");
        System.out.println("stu--   "+student);


    }
}
