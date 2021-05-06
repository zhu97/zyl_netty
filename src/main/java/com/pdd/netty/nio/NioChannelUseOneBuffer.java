package com.pdd.netty.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioChannelUseOneBuffer {

    public static void main(String[] args) throws IOException {
        File file = new File("./hello.txt");
        File file2 = new File("./hello2.txt");

        if (!file2.exists()) {
            file2.createNewFile();
        }

        ByteBuffer byteBuffer = ByteBuffer.allocate(2);

        FileInputStream fileInputStream = new FileInputStream(file);

        FileOutputStream fileOutputStream = new FileOutputStream(file2);

        while (true) {

            byteBuffer.clear();
            FileChannel channel = fileInputStream.getChannel();
            int read = channel.read(byteBuffer);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            FileChannel channel2 = fileOutputStream.getChannel();
            channel2.write(byteBuffer);
        }

        fileOutputStream.close();
        fileInputStream.close();
    }
}
