package udp_test;

import com.google.protobuf.DynamicMessage;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.handler.codec.DatagramPacketDecoder;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/15 15:01
 * Description:
 */
public class SimpleUdpServerInitializer extends ChannelInitializer<NioDatagramChannel> {
    @Override
    protected void initChannel(NioDatagramChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("handler", new SimpleUdpServerHandler());
    }
}
