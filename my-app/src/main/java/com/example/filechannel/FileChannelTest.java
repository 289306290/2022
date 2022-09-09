package com.example.filechannel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * 测试0.9G文件，FileChannel的效率
 * 耗时=703ms
 */
public class FileChannelTest {
    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        File sourceFile = new File("/Users/wujingjian/Downloads/jdk-18_linux-x64_bin.tar.gz");
        FileInputStream fis = new FileInputStream(sourceFile);
        FileChannel fisChannel = fis.getChannel();
        FileOutputStream fos = new FileOutputStream("/Users/wujingjian/Downloads/a.tar.gz");
        FileChannel fosChannel = fos.getChannel();
        //将fisChannel通道的数据，写入到fosChannel通道
        fisChannel.transferTo(0, fisChannel.size(), fosChannel);
        fis.close();
        fos.close();
        long end = System.currentTimeMillis();
        System.out.println("耗时=" + (end - start) + "ms");
    }
}