package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by blockWilling on 2019/3/15.
 */
public class ServerSocketChannelDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel open = ServerSocketChannel.open();
        open.bind(new InetSocketAddress(8080));
        open.configureBlocking(false);
//        Socket accept = open.socket().accept();
        ByteBuffer allocate1= ByteBuffer.allocate(1024);
        allocate1.putChar('1');
        allocate1.flip();
        SocketChannel accept =null;
        Selector selector = Selector.open();
        open.register(selector, SelectionKey.OP_ACCEPT);
        selector.select();
        while((accept = open.accept())==null){
        }
//        int write = accept.write(allocate1);

        System.out.println();
    }
}
