package server.redisMQ;

import redis.clients.jedis.Jedis;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/18 15:52
 * Description:
 */
public class PubClient {

    private Jedis jedis;

    public PubClient(String host, int port) {
        jedis = new Jedis(host, port, 10000);
    }

    public void pub(String channel, String message) {
        jedis.publish(channel, message);
    }

    public void close(String channel) {
        jedis.publish(channel, "quit");
        jedis.del(channel);
    }

}