package client;

import java.io.IOException;
import java.util.logging.*;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/16 0:25
 * Description:
 */

/**
 * Java自带Logging有SocketHandler，但是是基于TCP发送的
 * 可以继承StreamHandler改成UDP的
 */
public class Logging {

    static class MyLogFormatter extends Formatter {
        @Override
        public String format(LogRecord record) {
            return record.getLevel() + ":" + record.getMessage()+"\n";
        }
    }

    public static void main(String[] args) throws IOException {
        Logger log = Logger.getLogger("lavasoft");
        log.setLevel(Level.INFO);
        Logger log1 = Logger.getLogger("lavasoft");
        System.out.println(log==log1);     //true
        Logger log2 = Logger.getLogger("lavasoft.blog");

        ConsoleHandler consoleHandler =new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        log.addHandler(consoleHandler);

        FileHandler fileHandler = new FileHandler("E:/testlog%g.log");
        fileHandler.setLevel(Level.INFO);
        log.addHandler(fileHandler);
        fileHandler.setFormatter(new MyLogFormatter());

        log.info("aaa");
        log2.info("bbb");
        log2.fine("fine");
    }
}
