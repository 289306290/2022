package com.springboot.controller;

import com.springboot.vo.Computer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/computer")
public class StudentController {

    @RequestMapping("/getOne")
    public Computer getOne() {
        Computer computer = new Computer();
        computer.setId(1);
        computer.setName("联想");
        return computer;
    }

    @RequestMapping("/createOne")
    public Computer createOne(String name, Date createDate) {
        Computer computer = new Computer();
        computer.setId(2);
        computer.setName(name);
        computer.setCreateDate(createDate);
        return computer;
    }
}
