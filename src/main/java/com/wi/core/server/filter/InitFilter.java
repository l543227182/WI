package com.wi.core.server.filter;

import com.wi.core.server.handlers.Hander1;
import com.wi.core.server.handlers.Hander2;
import com.wi.core.server.handlers.HttpRequestHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class InitFilter extends ChannelInitializer<Channel> {
    protected void initChannel(Channel channel) throws Exception {
        ChannelPipeline ph = channel.pipeline();
       // ph.addLast("encoder",new HttpResponseEncoder());
      //  ph.addLast("decoder",new HttpRequestDecoder());
        // ph.addLast("handler", new HttpRequestHandler());
        ph.addLast(new StringDecoder());
        ph.addLast(new StringEncoder());
        ph.addLast(new Hander1());
        ph.addLast(new Hander2());
    }
}
