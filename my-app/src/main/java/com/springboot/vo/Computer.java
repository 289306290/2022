package com.springboot.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Computer {
    private long id;
    private String name;
    private Date createDate;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
