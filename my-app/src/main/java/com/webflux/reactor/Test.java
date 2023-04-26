package com.webflux.reactor;

import com.springboot.vo.Computer;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.DisposableServer;
import reactor.netty.http.server.HttpServer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

public class Test {

    public static void main(String[] args) throws InterruptedException {
//        test();
//        just();
//        otherJust();
//        server();
//        testWebClient();
//        testFlux();
//        backPressureTest();
        createNumberInterval();
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

    /**
     * Reactive Programming with Reactor 3
     * https://tech.io/playgrounds/929/reactive-programming-with-reactor-3/Intro
     */

    public static void testFlux(){
        List<String> words = Arrays.asList(
                "the", "quick", "brown", "fox",
                "jumped", "over", "the", "lazy", "dog");
        /*Flux.fromIterable(words)
                .flatMap(word -> Flux.fromArray(word.split("")))
                .concatWith(Mono.just("s")).distinct().sort()
                .zipWith(Flux.range(1, Integer.MAX_VALUE),
                        (string, count) ->
                                String.format("%2d. %s", count, string)
                )
                .subscribe(System.out::println);*/
        Flux.fromIterable(words).log()
                .flatMap(word-> Flux.fromArray(word.split("")))
                .concatWith(Mono.just("s")).distinct().sort().
        zipWith(Flux.range(1, Integer.MAX_VALUE),
                (string, count) ->
                        String.format("%2d. %s", count, string)).subscribe(System.out::println);
    }


    /**
     * 这个需要jdk9
     * static final int DEFAULT_BUFFER_SIZE = 256;
     * 这里我们发布 500 条数据，由于 Flow 的缓存大小是 256 ，所以前 256 条数据很快就生产出来进入缓存队列了
     */
    public static void backPressureTest() {

        SubmissionPublisher<String> publisher = new SubmissionPublisher<>();

        Flow.Subscriber<String> subscriber = new Flow.Subscriber<String>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                this.subscription = subscription;
                //向数据发布者请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                System.out.println("接收到 publisher 发来的消息了：" + item);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.subscription.request(1);
            }

            @Override
            public void onError(Throwable throwable) {
                //出现异常，就会来到这个方法，此时直接取消订阅即可
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                //发布者的所有数据都被接收，并且发布者已经关闭
                System.out.println("数据接收完毕");
            }
        };
        publisher.subscribe(subscriber);
        for (int i = 0; i < 500; i++) {
            System.out.println("i--------->" + i);
            publisher.submit("hello:" + i);
        }
        //关闭发布者
        publisher.close();
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

    public static void createNumberInterval() throws InterruptedException {
        Flux.<Long>interval(Duration.ofMillis(100)).take(10).subscribe(System.out::println);
        Thread.sleep(10000l);
    }


}
