package com.test1;

public class TestThread extends Thread {
    private int count = 5;

    public TestThread(String name) {
        super();
        this.setName(name);
    }

    @Override
    public void run(){
        super.run();
        while (count > 0) {
            count--;
            System.out.println("由"+ this.currentThread().getName() + "计算,count=" + count);
        }
    }

    public static void main(String[] args) {
        TestThread t1 = new TestThread("A");
        TestThread t2 = new TestThread("B");
        TestThread t3 = new TestThread("C");
        t1.start();
        t2.start();
        t3.start();
        /** 运行结果:
         * 由A计算,count=4
         * 由A计算,count=3
         * 由A计算,count=2
         * 由A计算,count=1
         * 由A计算,count=0
         * 由C计算,count=4
         * 由C计算,count=3
         * 由C计算,count=2
         * 由C计算,count=1
         * 由B计算,count=4
         * 由C计算,count=0
         * 由B计算,count=3
         * 由B计算,count=2
         * 由B计算,count=1
         * 由B计算,count=0
         */
    }
}
