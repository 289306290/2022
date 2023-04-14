package com.springboot.commandline;

import com.springboot.context.SpringContextHold;
import com.springboot.controller.StudentController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("这里是MyCommandLineRunner输出:"+args);
        StudentController t = SpringContextHold.getBean("studentController");
        System.out.println(t);
        t.getList();
    }
}
