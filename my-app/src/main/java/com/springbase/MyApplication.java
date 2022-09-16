package com.springbase;

import com.springbase.config.MainConfig;
import com.springbase.config.MyAnnotationConfigApplicationContext;
import com.springbase.context.SpringContextHolder;
import com.springbase.vo.Animal;
import com.springbase.vo.Student;
import org.springframework.context.ApplicationEvent;

import java.util.Arrays;

public class MyApplication {
    public static void main(String[] args) {
        MyAnnotationConfigApplicationContext configApplicationContext = new MyAnnotationConfigApplicationContext(MainConfig.class);
//        System.out.println(configApplicationContext.getBean("personVo"));
        String[] beanNames = configApplicationContext.getBeanDefinitionNames();
        Arrays.stream(beanNames).forEach(name -> System.out.println(configApplicationContext.getBean(name)));

        Student student = SpringContextHolder.getBean("student");
        System.out.println("stu--   "+student);
        System.out.println("stu--age   "+student.getAge());


        Animal animal =  SpringContextHolder.getBean("animal");
        System.out.println("animal的名字是屬性注入的另一種方式实现的" + animal.getName());



        configApplicationContext.publishEvent(new ApplicationEvent("我发布事情") {
            @Override
            public Object getSource() {
                return super.getSource();
            }
        });
        configApplicationContext.close();
    }
}
