package com.webflux.reactor;

import reactor.core.publisher.Flux;

//Flux代表多个
//Mongo代表1个或0个
public class ReactorUserService {
    // 返回的是多个，即Flux
    public Flux<Long> getFavorites(Long userId) {
        // 创建Flux并返回
        return Flux.create(sink -> {
            new Thread(() -> {
                // 模拟数据库访问时间
                try {
                    Thread.sleep(1000);
                    // 发布数据 1,2,3
                    System.out.println("发布数据1L");
                    sink.next(1L);
                    System.out.println("发布数据2L");
                    sink.next(2L);
                    System.out.println("发布数据3L");
                    sink.next(3L);
                    // 标识发布结束
                    sink.complete();
                    System.out.println("发布数据1L 2L 3L 结束");
                } catch (Exception e) {
                    e.printStackTrace();
                    sink.error(new Exception("读取出现错误"));
                }
            }).start();
        });
    }
}
