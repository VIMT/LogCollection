package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/18 13:54
 * Description:
 */
public class RedisJava {

    @Test
    public void testConnect() {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("149.56.225.59");
        System.out.println("Connection to server successfully");
        //查看服务是否运行
        System.out.println("Server is running: " + jedis.ping());
    }
}
