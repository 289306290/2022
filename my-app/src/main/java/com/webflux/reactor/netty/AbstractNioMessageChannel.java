package com.webflux.reactor.netty;

import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractNioMessageChannel extends AbstractNioChannel {
    /**
     * 读取到的缓存
     */
    private final List<Object> readBuf = new ArrayList<Object>();

    public AbstractNioMessageChannel(Channel parent, SelectableChannel ch, int readInterestOp) {
        super(parent, ch, readInterestOp);
    }

    /**
     * 从SelectableChannel中读取信息
     */
    @Override
    public void read() {
        final ChannelPipeline pipeline = pipeline();
        // 实际读取信息，由子类实现
        doReadMessages(readBuf);
        int size = readBuf.size();
        for (int i = 0; i < size; i ++) {
            // 调用管道的read处理器
            pipeline.fireChannelRead(readBuf.get(i));
        }
        readBuf.clear();
    }

    protected abstract int doReadMessages(List<Object> buf);
}
