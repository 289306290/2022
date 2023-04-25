package com.webflux.reactor.netty;

import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.Executor;

public class NioEventLoopGroup extends MultithreadEventLoopGroup {

    public NioEventLoopGroup(int nThreads) {
        super(nThreads, null);
    }

    public NioEventLoopGroup(int nThreads, Executor executor) {
        super(nThreads, executor);
    }

    @Override
    protected EventLoop newChild(Executor executor) {
        return new NioEventLoop(this, SelectorProvider.provider(), executor);
    }
}
