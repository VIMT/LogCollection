package client;


/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/16 0:29
 * Description:
 */

/**
 * 使用org.apache.logging.log4j.core.appender.SocketAppender
 * 具体用法可以参考官方的example
 * https://github.com/apache/logging-log4j2
 *
 * logstash-gelf  这个也是一个UDP的插件
 */
public class Log4j {

    /*
log4j-server.properties:

log4j.rootLogger=DEBUG, server
log4j.appender.server=org.apache.log4j.net.SocketAppender
log4j.appender.server.Port=4712
log4j.appender.server.protocol=UDP
log4j.appender.server.RemoteHost=loghost
log4j.appender.server.ReconnectionDelay=10000

     */
}
