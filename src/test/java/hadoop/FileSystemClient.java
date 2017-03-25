package hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * User: Tao
 * Email: 562593188@qq.com
 * Time: 2017/3/24 16:23
 * Description:
 */
public class FileSystemClient {

    FileSystem fs = null;

    @Before
    public void getFs() throws IOException {
        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://127.0.0.1:9000/");
        conf.set("dfs.replication", "1");
        fs = FileSystem.get(conf);
    }

    @Test
    public void testCopyLocalToHDFS() throws IOException {
        FSDataOutputStream os = fs.create(new Path("hdfs://127.0.0.1:9000/dst.txt"));
        FileInputStream is = new FileInputStream("D:\\test.txt");
        IOUtils.copyBytes(is, os, fs.getConf());
    }

    @Test
    public void testCopyLocalToHDFSSimple() throws IOException {
        fs.copyFromLocalFile(new Path("D:\\test.txt"), new Path("/dst2.txt"));
        FileStatus[] files = fs.listStatus(new Path("/"));

        for (FileStatus file : files) {
            System.out.println(file.getPath());
        }
    }
}
