package com.example;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

public class Test {
    public static void test1(String[] args) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(new File("/Users/wujingjian/test/zhaoyan/a.txt"),"rw");
        randomAccessFile.writeBytes("WJ");
        System.out.println("over");
    }


    static List<People> peopleList = Arrays.asList(
            new People("张三",2),
            new People("李四",15),
            new People("tom",8),
            new People("jack",10),
            new People("lili",8),
            new People("lucy",2));

    public static void main(String[] args) {
//        testComputeIfAbsent();

        System.out.println(16 & 32);
//        Unsafe.getUnsafe();//会报错 不能直接这么用,需要用到反射来使用这个Unsafe
    }

    private static  void testComputeIfAbsent(){
        //声明接收结果的 map
        Map<Integer, List<People>> resultMap = new HashMap<Integer, List<People>>();
        //按照年龄分组
        for (People people : peopleList) {
            //putIfAbsent方法，只有在key不存在或者key为null的时候，value值才会被覆盖
            // 返回的旧值，如果key不存在则返回null
            List<People> s = resultMap.putIfAbsent(people.getAge(), new ArrayList<People>());
            if(s==null) {//此时重新获取一次，就能到初始化好的List数组
                s = resultMap.putIfAbsent(people.getAge(), new ArrayList<People>());
            }
            s.add(people);
        }
        System.out.println(resultMap);

        resultMap = new HashMap<Integer, List<People>>();
        //对5岁以上的人进行分组
        for (People people : peopleList) {
            //如果value值不存在，返回的是新的value值
            //如果value存在，则返回的是旧值
            List<People> s = resultMap.computeIfAbsent(people.getAge(), k ->{
                if(k>5) {
                    return new ArrayList<People>();
                }
                return null;
            });

            if(s!=null) {
                s.add(people);
            }
        }
        System.out.println(resultMap);
    }


    public static void main3(String[] args) {
        Thread thread2;
        Thread thread1;
        thread2= new Thread(() -> {
            synchronized (Test.class) {
                try {
                    System.out.println("hello world2 start");
                    Test.class.notify();
                    Thread.sleep(1000L);
                    System.out.println("hello world2 end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1 = new Thread(() -> {
            synchronized (Test.class) {
                try {
                    thread2.start();
                    System.out.println("hello world1 start");
                    Thread.sleep(5000L);
                    Test.class.wait();
                    System.out.println("hello world1 end");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread1.start();
    }

    public static void main4(String[] args) throws InterruptedException {
        Thread thread2;
        Thread thread1;
        thread1= new Thread(() -> {

            System.out.println("1");
        });
        thread2= new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2");
        });
        thread2.join();
        thread2.start();
        thread1.start();
        thread1.join();
        System.out.println("3");
    }
}
