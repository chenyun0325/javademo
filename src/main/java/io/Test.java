package io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chenyun on 2020/5/18.
 */
public class Test {

    public static void main(String[] args) {
        nio();
    }
    public static void nio() {
        RandomAccessFile aFile = null;
        try {

            aFile = new RandomAccessFile("/Users/chenyun/workspace/javademo/src/main/java/io/data.txt", "rw");
            // channel获取数据
            FileChannel fileChannel = aFile.getChannel();
            // 初始化Buffer，设定Buffer每次可以存储数据量
            // 创建的Buffer是1024byte的，如果实际数据本身就小于1024，那么limit就是实际数据大小
            ByteBuffer buf = ByteBuffer.allocate(1024);
            // channel中的数据写入Buffer
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);

            while (bytesRead != -1) {
                // Buffer切换为读取模式
                buf.flip();
                // 读取数据
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                // 清空Buffer区
                buf.compact();
                // 继续将数据写入缓存区
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
