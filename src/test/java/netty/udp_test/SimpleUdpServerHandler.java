package netty.udp_test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/15 14:47
 * Description:
 */
public class SimpleUdpServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        InetAddress address = msg.sender().getAddress();
        ByteBuf buf = msg.content();
        byte[] rcvPktBuf = new byte[buf.readableBytes()];
        String str = new String(rcvPktBuf);
        String s = buf.toString();

        String req = msg.content().toString(CharsetUtil.UTF_8);

        if (req.trim().equals("getTime")) {
            System.out.println("receive req, from " + msg.sender().getHostName() + ":" + msg.sender().getPort());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = sdf.format(new Date());
            ctx.writeAndFlush(new DatagramPacket(
                    Unpooled.copiedBuffer("current time:" + dateStr, CharsetUtil.UTF_8), msg.sender()));
        }


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println(incoming.remoteAddress() + "Decode异常");
        cause.printStackTrace();
        ctx.close();
    }

}
