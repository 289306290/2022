package com.springboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.springboot.vo.Computer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/computer")
public class StudentController {

    @RequestMapping("/getOne")
    @JsonView(Computer.SimpleView.class)
    public Computer getOne() {
        Computer computer = new Computer();
        computer.setId(1);
        computer.setName("联想");
        computer.setPassword("aa");
        return computer;
    }

    @RequestMapping("/list")
    @JsonView(Computer.SimpleView.class)
    public List<Computer> getList() {
        Computer computer = new Computer();
        computer.setId(1);
        computer.setName("联想");
        computer.setPassword("aa");
        List<Computer> result = new ArrayList<>();
        result.add(computer);
        return result;
    }

    @RequestMapping("/listDetail")
    @JsonView(Computer.DetailView.class)
    public List<Computer> getListDetail() {
        Computer computer = new Computer();
        computer.setId(1);
        computer.setName("联想");
        computer.setPassword("aa");
        List<Computer> result = new ArrayList<>();
        result.add(computer);
        return result;
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
