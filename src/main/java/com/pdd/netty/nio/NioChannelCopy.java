package com.pdd.netty.nio;

import java.io.*;
import java.nio.channels.FileChannel;

public class NioChannelCopy {

    public static void main(String[] args) throws IOException {
        File file = new File("./hello.txt");

        File file2 = new File("./hello3.txt");

        FileInputStream inputStream = new FileInputStream(file);
        FileOutputStream outputStream = new FileOutputStream(file2);

        FileChannel channel = inputStream.getChannel();
        FileChannel channel1 = outputStream.getChannel();

        channel1.transferFrom(channel, 0, file.length());

        outputStream.close();

        inputStream.close();

    }
}
