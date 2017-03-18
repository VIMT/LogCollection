package server.redisMQ;

import redis.clients.jedis.JedisPubSub;

import java.util.Date;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/18 15:52
 * Description:
 */
public class MessageHandler extends JedisPubSub {

    @Override
    public void onMessage(String channel, String message) {
        String time = new Date().toString();
        System.out.println("message receive:" + message + ",channel:" + channel + "..." + time);
        //此处我们可以取消订阅
        if(message.equalsIgnoreCase("quit")){
            this.unsubscribe(channel);
        }
    }
}