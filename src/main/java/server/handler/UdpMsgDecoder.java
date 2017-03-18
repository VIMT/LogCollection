package server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/15 14:47
 * Description:
 */
public class UdpMsgDecoder extends SimpleChannelInboundHandler<DatagramPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        InetAddress address = msg.sender().getAddress();
        ByteBuf buf = msg.content();
        byte[] rcvPktBuf = new byte[buf.readableBytes()];
        ctx.fireChannelRead(msg.content().toString(CharsetUtil.UTF_8));

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println(incoming.remoteAddress() + "Decode异常");
        cause.printStackTrace();
        ctx.close();
    }

}
