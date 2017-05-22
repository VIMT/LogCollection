package server;

import server.storage.HDFSClient;
import server.redisMQ.RedisClient;

/**
 * @author litao5@xiaomi.com
 * @since 17-5-22 下午8:31
 */
public class StorageServer {

    private static final HDFSClient hdfsClient = new HDFSClient();
    private static final RedisClient redisClient = new RedisClient();

    private String collect() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            String log = redisClient.getLog();
            sb.append(log).append("\n");
        }
        return sb.toString();
    }

    private void store(String message) {
        hdfsClient.appendFile(message);
    }

    public void run() {
        while (true) {
            String collect = collect();
            store(collect);
        }
    }

    public static void main(String[] args) {
        new StorageServer().run();
    }
}
