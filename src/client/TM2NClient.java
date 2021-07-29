package com.dankhjay;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class TM2NClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket();
        socket.bind(new InetSocketAddress(9900));
        System.out.println(socket);
        socket.connect(new InetSocketAddress("localhost", 6666));
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        String message = "";
        while (message != "KillThread") {
            message = scanner.nextLine();
            dataOutputStream.writeUTF(message);
            dataOutputStream.flush();
        }
        dataOutputStream.close();
        socket.close();
    }
}
