package com.example.thread.cyclicbarrirer;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


/**
 index: 0
 index: 2
 index: 1
 index: 3
 index: 4
 index: 5
 index: 6
 index: 7
 index: 8
 index: 9
 所有人员到大屏障，准备开始执行任务
 子线程9 也阻塞,等所有现场都到了的时候，一起执行
 子线程1 也阻塞,等所有现场都到了的时候，一起执行
 子线程0 也阻塞,等所有现场都到了的时候，一起执行
 子线程2 也阻塞,等所有现场都到了的时候，一起执行
 子线程4 也阻塞,等所有现场都到了的时候，一起执行
 子线程3 也阻塞,等所有现场都到了的时候，一起执行
 子线程7 也阻塞,等所有现场都到了的时候，一起执行
 子线程6 也阻塞,等所有现场都到了的时候，一起执行
 子线程5 也阻塞,等所有现场都到了的时候，一起执行
 全部到达屏障
 子线程8 也阻塞,等所有现场都到了的时候，一起执行

 CountDownLatch: 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行。
 CyclicBrrier: N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。


 CountDownLatch当其他线程只要执行到门闩值为0，主线程就可以执行，
 而在这期间，其他线程也可以继续执行下去。
 而CyclicBarrier是N个线程之间互相等待，只有等到所有线程都执行完某一状态才可以接着执行下去


 CyclicBarrier比如王者荣耀，需要等5个人都准备好了,然后一起进入游戏(5个人需要相互等待)
 */
public class CyclicBarriertTest implements Runnable{
    private CyclicBarrier cyclicBarrier;
    private int index;

    public CyclicBarriertTest(CyclicBarrier cyclicBarrier, int index) {
        this.cyclicBarrier = cyclicBarrier;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            System.out.println("index: " + index);
            cyclicBarrier.await();
            System.out.println("子线程" + index +" 也阻塞,等所有现场都到了的时候，一起执行");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(11, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有人员到大屏障，准备开始执行任务");
            }
        });

        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarriertTest(cyclicBarrier,i)).start();
        }
        cyclicBarrier.await();

        /*
        cyclicBarrier使用过之后，可以重复使用
        for (int i = 0; i < 10; i++) {
            new Thread(new CyclicBarriertTest(cyclicBarrier,i)).start();
        }
        cyclicBarrier.await();*/

        System.out.println("全部到达屏障");
    }
}
