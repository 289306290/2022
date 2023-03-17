package com.spi.dubbo.service;

import com.alibaba.dubbo.common.extension.SPI;

@SPI("impl")
public interface DubboPrintService {
    public void printInfo(String str);
}
