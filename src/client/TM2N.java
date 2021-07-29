package com.dankhjay;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


// Project Take Me to Naver - Server
public class TM2N {
    public static Socket socket;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(6666);
        ExecutorService executorService = Executors.newFixedThreadPool(20);

        while (true) {
            socket = serverSocket.accept();
            System.out.println("New client is connected");
            Runnable runnable = () -> {
                String clientMsg = "";
                DataInputStream dataInputStream = null;
                try {
                    dataInputStream = new DataInputStream(socket.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                while (clientMsg != "KillThread") {
                    try {
                        clientMsg = dataInputStream.readUTF();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(clientMsg);
                    System.out.println(Thread.currentThread().getName());
                }
            };
            executorService.submit(runnable);
        }
    }
}
