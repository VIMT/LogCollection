package udp_test;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import org.junit.Test;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/15 14:58
 * Description:
 */
public class SimpleUdpServer {

    public void run() throws InterruptedException {
        //这个group到底是干嘛的，两个group和一个group有什么区别
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioDatagramChannel.class)
                    .handler(new SimpleUdpServerInitializer());

            System.out.println("UdpServer 启动了");

            //这一串都是干嘛的，sync  await?
            ChannelFuture f = b.bind(7070).sync();
            f.channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
            System.out.println("UdpServer 关闭了");
        }

    }

    public static void main(String[] args) throws InterruptedException {
        new SimpleUdpServer().run();
    }
}
