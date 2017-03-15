package edu.uestc.j8.server;

import edu.uestc.j8.server.handler.UdpServerInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/15 14:58
 * Description:
 */
public class UdpServer {

    private int port;

    public UdpServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //这个group到底是干嘛的，两个group和一个group有什么区别
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new UdpServerInitializer());

            System.out.println("UdpServer 启动了");

            //这一串都是干嘛的，sync  await?
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
            System.out.println("UdpServer 关闭了");
        }

    }

    public static void main(String[] args) throws Exception {

        new UdpServer(7070).run();
    }
}
