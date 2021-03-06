package server.storage;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import utils.Layouts;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author litao5@xiaomi.com
 * @since 17-5-22 下午7:33
 */
public class HDFSClient {
    private static final Layouts LAYOUTS = Layouts.getInstance();

    private static final FileSystem fs = getFs();

    private static FileSystem getFs() {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", LAYOUTS.getHdfsHost());
        conf.set("dfs.replication", "1");
        conf.set("dfs.support.append", "true");
        try {
            return FileSystem.get(conf);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void appendFile(String content) {
        InputStream in = new ByteArrayInputStream(content.getBytes());
        OutputStream out = null;

        Path logPath = new Path(LAYOUTS.getHdfsLogPath());

        try {
            boolean exists = fs.exists(logPath);
            if (!exists) {
                fs.create(logPath).close();
            }
            out = fs.append(logPath);
            IOUtils.copyBytes(in, out, 4096, true);
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
