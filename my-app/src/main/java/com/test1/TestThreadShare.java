package com.test1;

public class TestThreadShare extends Thread {
    private int count = 3;


    @Override
    public void run(){
        super.run();
        count--;
        System.out.println("由"+ this.currentThread().getName() + "计算,count=" + count);
    }

    public static void main(String[] args) {
        TestThreadShare thread = new TestThreadShare();
        Thread t1 = new Thread(thread);
        Thread t2 = new Thread(thread);
        Thread t3 = new Thread(thread);

        t1.start();
        t2.start();
        t3.start();
        /** 运行结果: 不确定,以下是某次的结果
         * 由Thread-2计算,count=1
         * 由Thread-1计算,count=1
         * 由Thread-3计算,count=0
         */


    }


}
