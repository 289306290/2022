package com.spi.dubbo.service.impl;

import com.spi.dubbo.service.DubboPrintService;

public class DubboPrintServiceImpl implements DubboPrintService {
    @Override
    public void printInfo(String str) {
        System.out.println("dubbo-SPI这里是实现类1."+str);
    }
}
