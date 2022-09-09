package com.example;



import java.util.ArrayList;
import java.util.List;

public class TestGC {
    private static final  int _512KB=512*1024;
    private static final  int _1MB=1024*1024;
    private static final  int _6MB=6*1024*1024;
    private static final  int _7MB=7*1024*1024;
    private static final  int _8MB=8*1024*1024;

    // 设置虚拟机参数：
    //-Xms20M -Xmx20M -Xmn10M -XX:+UseSerialGC -XX:+PrintGCDetails -verbose:gc -XX:+HeapDumpOnOutOfMemoryError
    public static  void main(String[] arg0) throws InterruptedException {
        List<byte[]> list = new ArrayList<>();
        Thread.sleep(3 * 1000);
        System.out.println(System.currentTimeMillis());
        list.add(new byte[_7MB]);
        list.add(new byte[_7MB]);
        list.add(new byte[_7MB]);
        System.out.println(System.currentTimeMillis());
//        list.add(new byte[_7MB]);
//        list.add(new byte[_7MB]);
//        Thread.sleep(1000 * 1000);

    }
}


