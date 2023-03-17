package com.spi.service.impl;

import com.spi.service.PrintService;

public class PrintServiceImpl implements PrintService {
    @Override
    public void printInfo(String str) {
        System.out.println("这里是实现类1."+str);
    }
}
