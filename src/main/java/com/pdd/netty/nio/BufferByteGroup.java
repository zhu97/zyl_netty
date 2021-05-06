package com.pdd.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class BufferByteGroup {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        SocketAddress socketAddress = new InetSocketAddress(6666);

        serverSocket.bind(socketAddress);
        SocketChannel accept = serverSocket.accept();

        ByteBuffer[] byteBuffer = new ByteBuffer[2];

        for (int i = 0; i < byteBuffer.length; i++) {
            byteBuffer[i] = ByteBuffer.allocate(8);
        }

        while (true) {
            long read = accept.read(byteBuffer);
            Arrays.asList(byteBuffer).forEach(ByteBuffer::flip);
            long write = 0;
            while (write < read) {
                write = accept.write(byteBuffer);
                System.out.println(Arrays.toString(byteBuffer));
            }

            Arrays.asList(byteBuffer).forEach(ByteBuffer::clear);
        }
    }
}
