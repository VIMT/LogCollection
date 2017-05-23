package utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigBeanFactory;
import com.typesafe.config.ConfigFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/18 16:43
 * Description:
 */
public class Layouts {
    private static final Layouts instance = new Layouts();

    private static final String FILENAME = "config.properties";

    private final String redisAddress;
    private final int redisPort;
    private final String redisLogName;
    private final String redisAuth;
    private final String hdfsHost;
    private final String hdfsLogPath;
    private final int storeTime;
    private final int storeSize;

    public static Layouts getInstance() {
        return instance;
    }

    private Layouts() {
        Config config = getConfig();
        redisAddress = config.getString("redis.address");
        redisPort = config.getInt("redis.port");
        redisLogName = config.getString("redis.log.name");
        redisAuth = config.getString("redis.auth");
        hdfsHost = config.getString("hdfs.host");
        hdfsLogPath = hdfsHost + config.getString("hdfs.log.path");
        storeTime = config.getInt("store.time");
        storeSize = config.getInt("store.size");
    }

    private Config getConfig() {
        return ConfigFactory.load("config.properties");
    }

    public String getRedisAddress() {
        return redisAddress;
    }

    public int getRedisPort() {
        return redisPort;
    }

    public String getRedisLogName() {
        return redisLogName;
    }

    public String getHdfsHost() {
        return hdfsHost;
    }

    public String getHdfsLogPath() {
        return hdfsLogPath;
    }

    public String getRedisAuth() {
        return redisAuth;
    }

    public int getStoreTime() {
        return storeTime;
    }

    public int getStoreSize() {
        return storeSize;
    }
}
