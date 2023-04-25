package com.springboot.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.springboot.vo.Computer;
import org.openjdk.jmh.annotations.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/computer")
public class StudentController {

    @RequestMapping("/{id}")
    @JsonView(Computer.SimpleView.class)
    public Computer getOne(@PathVariable("id") Long id) {
        Computer computer = new Computer();
        computer.setId(id);
        computer.setName("联想"+id);
        computer.setPassword("aa");
        return computer;
    }

    @RequestMapping("/list")
    @JsonView(Computer.DetailView.class)
    public List<Computer> getList() {
        Computer computer = new Computer();
        computer.setId(1);
        computer.setName("联想");
        computer.setPassword("aa");
        List<Computer> result = new ArrayList<>();
        result.add(computer);

        Computer computer10 = new Computer();
        computer10.setId(10);
        computer10.setName("联想10");
        computer10.setPassword("aa10");
        result.add(computer10);
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

    @GetMapping(value = "/stream",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> flux() {
        return Flux.create(sink->{
            new Thread(() -> {
                String[] arr = {"pq1", "pq2", "pq3"};
                for (int i=0;i<3;i++) {
                    try {Thread.sleep(1000);} catch (InterruptedException e) {}
                    sink.next(arr[i]);
                    if (i==2) {
                        sink.complete();
                    }
                }
            }).start();
        });
    }

    @GetMapping("/just")
    public Mono<String> getUser() {
        return Mono.just("pq");
    }

    @GetMapping("/computer")
    public Mono<Computer> getComputer() {
        return Mono.just(new Computer());
    }

}
