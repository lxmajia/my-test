package cn.xwlin.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;

public class ChatClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap().group(group).channel(NioSocketChannel.class)
                    .handler(new ChatClientInit());
            ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8090));
            future.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture f) throws Exception {
                    if (f.isSuccess()) {
                        System.out.println("连接成功");
                    } else {
                        // 如果启动失败又重启
                        System.out.println("连接失败");
                    }
                }
            });
            Channel channel = future.sync().channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}