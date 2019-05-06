package com.wi.core.server.filter;

import com.wi.core.server.handlers.HttpRequestHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class InitFilter extends ChannelInitializer<Channel> {
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline ph = channel.pipeline();
        ph.addLast("encoder",new HttpResponseEncoder());
        ph.addLast("decoder",new HttpRequestDecoder());
        ph.addLast("aggregator", new HttpObjectAggregator(10*1024*1024));
        ph.addLast("handler", new HttpRequestHandler());
    }
}
