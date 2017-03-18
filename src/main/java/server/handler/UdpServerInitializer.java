package server.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/15 15:01
 * Description:
 */
public class UdpServerInitializer extends ChannelInitializer<NioDatagramChannel> {
    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("handler", new UdpMsgDecoder());
    }
}
