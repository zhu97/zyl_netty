package com.pdd.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioFileChannelWrite {

    public static void main(String[] args) throws IOException {
        String string = "hello, pdd";

        File file = new File("./hello.txt");

        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream("./hello.txt");

        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer byteBuffer =ByteBuffer.allocate(1024);

        byteBuffer.put(string.getBytes());

        byteBuffer.flip();

        channel.write(byteBuffer);

        fileOutputStream.close();
    }
}
