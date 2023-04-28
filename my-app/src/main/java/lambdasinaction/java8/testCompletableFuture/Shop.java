package lambdasinaction.java8.testCompletableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Shop {
    public double getPrice(String product) {
        //查询商店的数据库获取价格
        return 0;
    }

    public static double calculatePrice(String product) {
        System.out.println("开始执行计算方法");
        delay();
        System.out.println("计算方法延迟结束");
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try{
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Double> completableFuture = new CompletableFuture<>();
        double price = calculatePrice("productName");
        completableFuture.complete(price); //
        if (price == 0) {
            completableFuture.completeExceptionally(new RuntimeException("这里设置一个错误情况时候的异常"));
        }
        try {
            double result = completableFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //-----------

        double result = CompletableFuture.supplyAsync(() -> calculatePrice("produceName")).get();//这个和上面是等价的,同时有异常也会捕获到
        System.out.println("result-->"+result);

    }
}