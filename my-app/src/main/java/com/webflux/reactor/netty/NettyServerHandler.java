package com.webflux.reactor.netty;


import java.nio.ByteBuffer;

public class NettyServerHandler implements ChannelInboundHandler {

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("thread: " + Thread.currentThread().getName());
        System.out.println("msg: "+ new String(((ByteBuffer)msg).array()));
        //传递给下个handler
        ctx.fireChannelRead(msg);
    }
}
