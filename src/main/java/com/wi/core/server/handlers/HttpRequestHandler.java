package com.wi.core.server.handlers;

import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpRequestHandler  extends SimpleChannelInboundHandler<FullHttpRequest> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {
        System.out.println(fullHttpRequest.uri());
        channelHandlerContext.writeAndFlush(new String("hello man,your url is : " + fullHttpRequest.uri()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                .addListener(ChannelFutureListener.CLOSE);
    }
}
