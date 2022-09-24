package com.example.thread;

import java.util.concurrent.Semaphore;

/**
 *
 Thread-1: acquire() at time 1663746989035
 Thread-3: acquire() at time 1663746989035
 Thread-3: release() at time 1663746990040
 Thread-5: acquire() at time 1663746990041
 Thread-1: release() at time 1663746990040
 Thread-7: acquire() at time 1663746990041
 Thread-5: release() at time 1663746991044
 Thread-9: acquire() at time 1663746991044
 Thread-7: release() at time 1663746991044
 Thread-9: release() at time 1663746992048
 */

public class SemaphoreSample {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(2);
        for(int i=0; i < 5; i++) {
            new Thread(new Task(semaphore, "nice " + i)).start();
        }
    }

    static class Task extends Thread {
        Semaphore semaphore;

        public Task(Semaphore semaphore, String name) {
            this.semaphore = semaphore;
            this.setName(name);
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ": acquire() at time " + System.currentTimeMillis());
                Thread.sleep(1000);
                semaphore.release();
                System.out.println(Thread.currentThread().getName() + ": release() at time " + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
