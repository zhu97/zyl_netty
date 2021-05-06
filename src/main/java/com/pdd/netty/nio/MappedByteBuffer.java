package com.pdd.netty.nio;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class MappedByteBuffer {

    public static void main(String[] args) throws IOException {

        File file = new File("./hello.txt");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileChannel channel = randomAccessFile.getChannel();
        java.nio.MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
        mappedByteBuffer.put(0, (byte) 'N');
        mappedByteBuffer.put(3, (byte) '1');
        randomAccessFile.close();
    }
}
