package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by chenyun on 2020/5/18.
 */
public class NioTest {
    public static void main(String[] args) throws IOException {
        RandomAccessFile file = new RandomAccessFile("/Users/chenyun/workspace/javademo/src/main/java/io/data.txt", "rw");
        FileChannel channel = file.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(48);

        int bytesRead = channel.read(buffer);
        while (bytesRead != -1) {
            System.out.println("Read " + bytesRead);
            buffer.flip();
            while(buffer.hasRemaining()){
                System.out.print((char) buffer.get());
            }
            buffer.clear();
            bytesRead = channel.read(buffer);
        }
        channel.close();
    }
}
