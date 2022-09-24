package com.example.thread.CountDownLaunchTest;

import java.util.concurrent.CountDownLatch;

public class QueueTask implements Runnable {

    private CountDownLatch countDownLatch;

    public QueueTask(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("开始在药房派对买药");
            Thread.sleep(5000);
            System.out.println("排队成功，可以开始缴费买药");
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
