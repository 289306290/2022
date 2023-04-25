package com.webflux.reactor;

import com.springboot.vo.Computer;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.util.List;

public class Test {

    public static void main(String[] args) throws InterruptedException {
//        test();
//        just();
//        otherJust();
//        server();
        testWebClient();
    }

    public static void test() throws InterruptedException {
        ReactorUserService userService = new ReactorUserService();
        ReactorFavoriteService favoriteService = new ReactorFavoriteService();
        userService.getFavorites(23L)
                .flatMap(id->favoriteService.getDetail(id)) // 取详情
                .take(2) // 取前两个
                .subscribe(System.out::println, error-> System.out.println("process error:"+error)); // 订阅，处理方式sout
        while (true) {
            System.out.println("做点其它事。。。");
            Thread.sleep(1000);
        }
    }

    public static void just() {
        Flux.just("a", "b", "c").subscribe(new Subscriber<String>() {
            Subscription subscription;
            @Override
            public void onSubscribe(Subscription s) {
                this.subscription = s;
                subscription.request(1); // 请求1个
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext-->  "+s); // 响应
                subscription.request(1); // 再请求1个
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("completed"); // 完成
            }
        });
    }

    public static void otherJust() {
        Flux.just("a", "b", "c", "d").subscribe(System.out::println);
    }

    public static void server(){
        DisposableServer server =
                HttpServer.create() // 创建http服务
                        .port(7892) // 绑定端口
                        .route(routes -> // 路由
                                routes.get("/hello", (request, response) -> {
                                            return response.sendString(Mono.just("Hello World"));
                                        }
                                )
                        )
                        .bindNow();
        server.onDispose()
                .block();
    }


    //下面是webclient的两个示例
    private static void testWebClient() {

        WebClient webClient = WebClient.create();
        monoTest(webClient, "http://localhost:8088/computer/2");
        fluxTest(webClient, "http://localhost:8088/computer/list");

    }
    /**
     * 从 API 获取单个电脑
     */
    private static void monoTest(WebClient webClient, String uri) {
        //要注意此时实际上还没有发送任何请求！作为一个反应式 API，在某些尝试读取或等待响应之前，不会实际发送请求。
        Mono<Computer> postMono = webClient.get().uri(uri).retrieve().bodyToMono(Computer.class);
        Computer computer = postMono.blockOptional().get();
        System.out.println(computer.getName());
    }

    /**
     * 获取 post 列表 ，使用 Flux 因为是多值 , 要是获取一个对象比如  `posts/1` 就可以用 Mono
     *
     * @param webClient
     * @param uri
     */
    private static void fluxTest(WebClient webClient, String uri) {

        // retrieve() 方法是获取响应主体并对其进行解码
        Flux<Computer> postFlux = webClient.get().uri(uri).retrieve().bodyToFlux(Computer.class);
        List<Computer> computers = postFlux.collectList().block();
        Long idSum = computers.stream().mapToLong(post -> post.getId()).reduce(0, (a, b) -> a + b);
        System.out.println(idSum);
    }
}
