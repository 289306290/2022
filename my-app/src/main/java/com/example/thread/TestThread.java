package com.example.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class TestThread {

    public static void main(String[] args) {
//        TestThread.imp1();
//        TestThread.imp2();
//        TestThread.imp3();
        unpark();
    }

    /** synchronized   wait notify
     * 线程1 开始等待...
         线程2执行唤醒操作
         线程1 被唤醒
     */
    private static void imp1() {
        final Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " 开始等待...");
                try {lock.wait();} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName() + " 被唤醒");
            }
        }, "线程1").start();

        new Thread(() -> {
            synchronized (lock) {
                try {
                    TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
                System.out.println(Thread.currentThread().getName() + "执行唤醒操作");
                lock.notify();
            }
        }, "线程2").start();
    }


    /**
     * ReentrantLock  Condition的  await()   signal()
     */
    private static void imp2() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "{} 开始等待...");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "{} 被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "线程1").start();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(1);
                System.out.println(Thread.currentThread().getName() + "{} 执行唤醒操作");
                condition.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "线程2").start();
    }


    /**
     * LockSupport park() unpark()
     */
    private static void imp3() {
        Thread t1 = new Thread(() -> {
            try {TimeUnit.SECONDS.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            System.out.println(Thread.currentThread().getName() +"{} 开始等待...LockSupport");
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() +"{} 被唤醒");
        }, "线程1");

        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() +"{} 执行唤醒操作");
            LockSupport.unpark(t1);
        }, "线程2");
        t2.start();
    }


    /**
     * LockSupport.park()方法有时候不会阻塞
     */
    public static void unpark() {
        Thread t0 = new Thread(() -> {
            Thread current = Thread.currentThread();
            System.out.println("{},开始执行!"+current.getName());
            for(;;){//spin 自旋
                System.out.println("准备park住当前线程：{}...."+current.getName());
                LockSupport.park();
                /**
                 * 这是因为调用 t0.interrupt() 方法发出一个中断时，t0线程上面会一个中断状态（打上一个中断标记）。当发出的中断唤醒了 LockSupport.park() 方法阻塞住的线程时，park方法发现 t0线程有这个中断状态，知道该线程是需要被中断处理的，则下一次调用 LockSupport.park() 则不会再被阻塞了。

                 如果发出了中断后，唤醒了 LockSupport.park() 阻塞的线程，下一次遇到 LockSupport.park() 还想被阻塞住，就需要先调用 Thread.interrupted() 方法来清除中断状态，这样下一次遇到 LockSupport.park() 方法的时候，线程便会被阻塞住。
                 ————————————————
                 版权声明：本文为CSDN博主「mo_ing」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
                 原文链接：https://blog.csdn.net/mo_ing/article/details/120651364
                 */
                Thread.interrupted();//放开这行会发现每次调用LockSupport.park()方法还是会阻塞，如果注释掉此方法，则LockSupport.park()方法不会阻塞了
                System.out.println("当前线程{}已经被唤醒...."+current.getName());
            }
        },"t0");
        t0.start();
        try {
            Thread.sleep(2000);
            System.out.println("准备唤醒{}线程!"+t0.getName());
            LockSupport.unpark(t0);
            Thread.sleep(2000);
            //注意这里:
            //利用Thread类中的 public void interrupt() 方法，发出一个中断，也会唤醒park方法阻塞住的线程。
            //但是当下次再循环到park方法时，不再阻塞住线程，循环会一直执行。
            t0.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
