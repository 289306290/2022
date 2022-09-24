package com.example.thread.CountDownLaunchTest;

import java.util.concurrent.CountDownLatch;

public class SeeDoctorTask implements Runnable{
    private CountDownLatch countDownLatch;

    public SeeDoctorTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始看医生。。。。");
            Thread.sleep(3000);
            System.out.println("看医生结束。。。。。");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != countDownLatch) {
                countDownLatch.countDown();
                System.out.println("调用countDown()方法后子线程继续走");
            }
        }
    }
}
