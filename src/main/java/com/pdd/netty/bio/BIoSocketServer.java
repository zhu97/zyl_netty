package com.pdd.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIoSocketServer {

    public static void main(String[] args) throws IOException {
        int port = 6666;

        ServerSocket serverSocket = new ServerSocket(port);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        executorService.submit(() -> {
            try {
                Socket accept = serverSocket.accept();
                handle(accept);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });


    }

    private static void handle(Socket accept) throws IOException {
        while (true) {
            byte[] bytes = new byte[1024];
            InputStream inputStream = accept.getInputStream();
            int read = inputStream.read(bytes);
            if (read != -1) {
                System.out.println(new String(bytes, 0, read));
            } else {
                break;
            }
        }
    }
}
