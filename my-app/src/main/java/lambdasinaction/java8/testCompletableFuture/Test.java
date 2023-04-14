package lambdasinaction.java8.testCompletableFuture;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws IOException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()->{
            System.out.println("这里是线程:"+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(10l);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("这里是执行的任务逻辑");
            return "hello";
        });
        System.out.println(future.getNow("默认值"));
//        System.in.read();
    }
}
