package com.webflux.reactor.netty;

public interface EventLoop extends EventLoopGroup {
    EventLoopGroup parent();
}
