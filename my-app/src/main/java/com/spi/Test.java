package com.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.spi.dubbo.service.DubboPrintService;
import com.spi.service.PrintService;

import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {
        ServiceLoader<PrintService> serviceLoader = ServiceLoader.load(PrintService.class);
        for (PrintService printService : serviceLoader) {
            printService.printInfo("这里是参数");
        }
        DubboPrintService printService = ExtensionLoader.getExtensionLoader(DubboPrintService.class).getDefaultExtension();
        printService.printInfo("dubbo");
    }
}
