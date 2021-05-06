package com.pdd.netty.nio.groupTalk;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class GroupServer {

    private ServerSocketChannel listenChannel;

    private static final int port = 6667;

    private Selector selector;

    public GroupServer() {
        try {
            selector = Selector.open();
            listenChannel = ServerSocketChannel.open();
            listenChannel.socket().bind(new InetSocketAddress(port));

            listenChannel.configureBlocking(false);

            listenChannel.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            while (true) {
                int count = selector.select();
                if (count > 0) {
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            SocketChannel sc = listenChannel.accept();
                            sc.configureBlocking(false);
                            sc.register(selector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + "上线");

                        }

                        if (key.isReadable()) {
                            read(key);
                        }

                        iterator.remove();
                    }
                } else {
                    System.out.println("等待中。。。");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    private void read(SelectionKey key) {
        SocketChannel channel = null;

        try {
            channel = (SocketChannel) key.channel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            int count = channel.read(byteBuffer);

            if (count > 0) {
                String s = new String(byteBuffer.array());
                System.out.println("from 客户端 ： " + s);
                sendToOtherClients(s, channel);
            }

        } catch (IOException e) {
            try {
                System.out.println(channel.getRemoteAddress() + "离线了。。。");
                key.cancel();
                channel.close();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void sendToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("消息转发中。。。");
        for (SelectionKey selectionKey : selector.keys()) {
            Channel targetChannel = selectionKey.channel();
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel dest = (SocketChannel) targetChannel;
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());

                dest.write(buffer);
            }

        }
    }

    public static void main(String[] args) {
        GroupServer groupServer = new GroupServer();
        groupServer.listen();
    }

}
