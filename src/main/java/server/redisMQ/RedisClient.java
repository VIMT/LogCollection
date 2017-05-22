package server.redisMQ;

import redis.clients.jedis.Jedis;
import static utils.Constants.REDISHOST;
import static utils.Constants.LOGMQ;
import static utils.Constants.PORT;

import java.util.List;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/18 15:59
 * Description:
 */
public class RedisClient {

    private static final Jedis jedis = getJedis() ;

    private static Jedis getJedis() {
        Jedis jedis = new Jedis(REDISHOST, PORT, 10000);
        jedis.auth("");
        return jedis;
    }
    public void addLog(String msg) {
        jedis.lpush(LOGMQ, msg);
    }

    public String getLog() {
        List<String> pop = jedis.brpop(60, LOGMQ);
        //0为key, 1为value
        return pop.get(1);
    }
}