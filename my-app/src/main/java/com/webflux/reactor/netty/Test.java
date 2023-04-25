package com.webflux.reactor.netty;


/**
 * netty源码看不懂？试着写一个吧
 * https://www.jianshu.com/p/b76c974397fe
 */

public class Test {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerHandler());
            System.out.println("netty server start...");
            bootstrap.bind(9000);
        } finally {

        }
    }
}
