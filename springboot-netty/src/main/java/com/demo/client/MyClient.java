package com.demo.client;

import com.demo.client.handler.MyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * <p> @Title MyClient
 * <p> @Description 客户端启动类
 *
 * @author zhj
 * @date 2022/9/7 20:52
 */
public class MyClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            // 创建 bootstrap 对象，配置参数
            Bootstrap bootstrap = new Bootstrap();
            // 设置线程组
            Bootstrap handler = bootstrap.group(eventExecutors)
                    // 设置客户端的通道实现类
                    .channel(NioSocketChannel.class)
                    // 使用匿名内部类初始化通道
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            // 添加客户端通道的处理器
                            ch.pipeline().addLast(new MyClientHandler());
                        }
                    });
            System.out.println("客户端准备就绪，随时可以发起对话~");
            // 连接服务端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 1111).sync();
            // 对通道关闭进行监听
            channelFuture.channel().closeFuture().sync();
        } finally {
            // 关闭线程组
            eventExecutors.shutdownGracefully();
        }
    }
}
