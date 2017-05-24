package server.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import server.redisMQ.RedisClient;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/15 14:47
 * Description:
 */
public class UdpMsgDecoder extends SimpleChannelInboundHandler<String> {
    private static final RedisClient redisClient = new RedisClient();

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        redisClient.addLog(msg);
        System.out.println(msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        Channel incoming = ctx.channel();
        System.out.println(incoming.remoteAddress() + "消息异常");
        cause.printStackTrace();
        ctx.close();
    }
}
