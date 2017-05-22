package server.storage;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import static utils.Constants.HDFSHOST;
import static utils.Constants.LOGPATH;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author litao5@xiaomi.com
 * @since 17-5-22 下午7:33
 */
public class HDFSClient {

    private static final FileSystem fs = getFs();

    private static FileSystem getFs() {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", HDFSHOST);
        conf.set("dfs.replication", "1");
        try {
            return FileSystem.get(conf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void appendFile(String content) {
        InputStream in = new ByteArrayInputStream(content.getBytes());
        OutputStream out = null;

        Path logPath = new Path(LOGPATH);

        try {
            boolean exists = fs.exists(logPath);
            if (!exists) {
                fs.create(logPath);
            }
            out = fs.append(logPath);
            IOUtils.copyBytes(in, out, 4096, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
