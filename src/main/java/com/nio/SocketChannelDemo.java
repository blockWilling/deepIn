package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by blockWilling on 2019/3/15.
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel open = SocketChannel.open( );
        open.configureBlocking(false);
        boolean connect = open.connect(new InetSocketAddress(8080));
        while(!open.finishConnect()){
        }
        Thread.sleep(1000);
        ByteBuffer allocate1= ByteBuffer.allocate(1024);
        int read = open.read(allocate1);
        allocate1.rewind();
        System.out.println(allocate1.getChar());
        System.out.println();
    }
}
