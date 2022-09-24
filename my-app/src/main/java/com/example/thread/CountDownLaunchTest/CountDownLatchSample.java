package com.example.thread.CountDownLaunchTest;

import java.util.concurrent.CountDownLatch;


/**
 * 主线程 等待别的两个线程都执行完了 再执行
 *
 *
 开始看医生。。。。
 开始在药房派对买药
 看医生结束。。。。。
 调用countDown()方法后子线程继续走
 排队成功，可以开始缴费买药
 调用countDown()方法后子线程继续走
 一共花费了时间: 5010


 CountDownLatch在子线程调用countDown()方法后，子线程不会阻塞会继续执行



 CountDownLatch当其他线程只要执行到门闩值为0，主线程就可以执行，
 而在这期间，其他线程也可以继续执行下去。
 而CyclicBarrier是N个线程之间互相等待，只有等到所有线程都执行完某一状态才可以接着执行下去
 */
public class CountDownLatchSample {

    public CountDownLatchSample() {
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();

        CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(new SeeDoctorTask(countDownLatch)).start();
        new Thread(new QueueTask(countDownLatch)).start();

        //等待线程池中的两个任务执行完毕
        countDownLatch.await();
        System.out.println("一共花费了时间: " + (System.currentTimeMillis() - now));
    }
}
