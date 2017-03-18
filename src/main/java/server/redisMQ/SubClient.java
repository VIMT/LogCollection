package server.redisMQ;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/18 15:59
 * Description:
 */
public class SubClient {

    private Jedis jedis;//

    public SubClient(String host,int port){
        jedis = new Jedis(host, port, 10000);
    }

    public void sub(JedisPubSub listener, String channel){
        jedis.subscribe(listener, channel);
        //此处将会阻塞，在client代码级别为JedisPubSub在处理消息时，将会“独占”链接
        //并且采取了while循环的方式，侦听订阅的消息
        //
    }
}