package com.wi.core.server;

import com.wi.core.server.filter.InitFilter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class NettyServer {

    private final EventLoopGroup boss ;
    private final EventLoopGroup worker;
    private final ServerBootstrap bootstrap;

    public NettyServer(EventLoopGroup boss, EventLoopGroup worker) {
        this.boss = boss;
        this.worker = worker;
        this.bootstrap = new ServerBootstrap();
        this.init();
    }

    private void init() {
        try {
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .localAddress(new InetSocketAddress(8824))
                    .childHandler(new InitFilter());//保持连接
            ChannelFuture f = bootstrap.bind().sync();
            f.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("系统启动失败");
        }finally {
            try {
                boss.shutdownGracefully().sync();
                worker.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
