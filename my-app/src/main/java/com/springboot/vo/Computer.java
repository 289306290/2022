package com.springboot.vo;

import com.fasterxml.jackson.annotation.JsonView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Computer {
    @JsonView(DetailView.class)
    private long id;
    private String name;
    private Date createDate;
    private String password;

    public interface SimpleView{};
    public interface DetailView extends SimpleView{};

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
        System.out.println("赋值日期--->  "+new SimpleDateFormat("yyyy-MM-dd").format(createDate));
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonView(SimpleView.class)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonView(DetailView.class)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
