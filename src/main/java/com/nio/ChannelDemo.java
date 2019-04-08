package com.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Random;

/**
 * Created by blockWilling on 2019/3/15.
 */
public class ChannelDemo {
    public static void main(String[] args) throws IOException {
        RandomAccessFile test = new RandomAccessFile("E:\\BaiduNetdiskDownload\\架构学习\\deepIn\\src\\main\\resources\\fileCHannelTestFile.properties", "rw");
        FileChannel channel = test.getChannel();
//        FileLock lock = channel.tryLock();
        ByteBuffer allocate1= ByteBuffer.allocate(1024);
        ByteBuffer allocate2 = ByteBuffer.allocate(1024);
        allocate1.putChar('1');
        allocate2.putChar('2');
        allocate1.flip();
        allocate2.flip();
//        Thread.currentThread().interrupt();
//        int read = channel.read(allocate);
        new Thread(() -> {
            try {
                channel.write(allocate1);
                allocate1.flip();
                Thread.sleep(100);
                channel.write(allocate1);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                channel.write(allocate2);
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }

}

