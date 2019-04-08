package com.nio;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

/**
 * Created by blockWilling on 2019/3/14.
 */
public class BufferDemo {
    public static void main(String[] args) {
/*        CharBuffer buf = CharBuffer.allocate(48);
        buf.put('a');
        buf.flip();
        System.out.println(buf.get());
        buf.clear();
//        System.out.println(buf.get());
        buf.put('g');
        buf.flip();
        System.out.println(buf.get());
        System.out.println(buf.get());*/
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(128);
        byteBuffer.putInt(1);
        byteBuffer.asIntBuffer();
    }
}
