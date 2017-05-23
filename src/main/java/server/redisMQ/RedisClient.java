package server.redisMQ;

import redis.clients.jedis.Jedis;
import utils.Layouts;

import java.util.List;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/18 15:59
 * Description:
 */
public class RedisClient {

    private static final Layouts LAYOUTS = Layouts.getInstance();

    private static final Jedis jedis = getJedis();

    private static Jedis getJedis() {
        Jedis jedis = new Jedis(LAYOUTS.getRedisAddress(), LAYOUTS.getRedisPort(), 10000);
        jedis.auth(LAYOUTS.getRedisAuth());
        return jedis;
    }

    public void addLog(String msg) {
        jedis.lpush(LAYOUTS.getRedisLogName(), msg);
    }

    public String getLog() {
        List<String> pop = jedis.brpop(LAYOUTS.getStoreTime(), LAYOUTS.getRedisLogName());
        //0为key, 1为value
        if (pop.size() > 0) {
            return pop.get(1);
        }
        return null;
    }
}