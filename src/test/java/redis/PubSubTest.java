package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import utils.Constants;

import java.util.Date;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/18 16:15
 * Description:
 */
public class PubSubTest {

    static class SubClient {

        private Jedis jedis;//

        public SubClient(String host, int port) {
            jedis = new Jedis(host, port, 10000);
        }

        public void sub(JedisPubSub listener, String channel) {
            jedis.subscribe(listener, channel);
            //此处将会阻塞，在client代码级别为JedisPubSub在处理消息时，将会“独占”链接
            //并且采取了while循环的方式，侦听订阅的消息
            //
        }
    }

    static class PubClient {

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

    static class MessageHandler extends JedisPubSub {

        @Override
        public void onMessage(String channel, String message) {
            String time = new Date().toString();
            System.out.println("message receive:" + message + ",channel:" + channel + "..." + time);
            //此处我们可以取消订阅
            if (message.equalsIgnoreCase("quit")) {
                this.unsubscribe(channel);
            }
        }
    }

    @Test
    public static void test() throws Exception {
        PubClient pubClient = new PubClient(Constants.REDISHOST, Constants.PORT);
        final String channel = "pub";
        pubClient.pub(channel, "before1");
        pubClient.pub(channel, "before2");
        Thread.sleep(2000);

        //消息订阅者需要独占链接，因此我们需要为它创建新的链接；
        //此外，jredis客户端的实现也保证了“链接独占”的特性，sub方法将一直阻塞，
        //直到调用listener.unsubscribe方法

        Thread subThread = new Thread(() -> {
            try {
                SubClient subClient = new SubClient(Constants.REDISHOST, Constants.PORT);
                System.out.println("----------subscribe operation begin-------");
                JedisPubSub listener = new MessageHandler();
                //在API级别，此处为轮询操作，直到unsubscribe调用，才会返回
                subClient.sub(listener, channel);
                System.out.println("----------subscribe operation end-------");
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        subThread.start();
        int i = 0;
        while (i < 10) {
            String message = "test";//apache-commons
            pubClient.pub(channel, message);
            i++;
            Thread.sleep(1000);
        }
        //被动关闭指示，如果通道中，消息发布者确定通道需要关闭，那么就发送一个“quit”
        //那么在listener.onMessage()中接收到“quit”时，其他订阅client将执行“unsubscribe”操作。
        pubClient.close(channel);
        //此外，你还可以这样取消订阅
        //listener.unsubscribe(channel);

    }

}