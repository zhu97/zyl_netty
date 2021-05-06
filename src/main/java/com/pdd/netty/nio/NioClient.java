package com.pdd.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress(6666));
            while (!socketChannel.finishConnect()) {
                System.out.println("连接中。。。。。。");
            }
            String s = "hello, pdd " + i;
            ByteBuffer byteBuffer = ByteBuffer.wrap(s.getBytes());
            socketChannel.write(byteBuffer);
            System.out.println("发送完成");
            socketChannel.close();
        }

    }
}
