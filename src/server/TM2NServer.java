package com.TM2N;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TM2NServer {
    public static void main(String[] args) throws IOException {
        // Setup DB
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name of DB: ");
        String db = scanner.nextLine();
        // Register driver // Class.forName(db);
        // Create Connection Object // Connection con = DriverManager.getConnection(str, str, str);
        // Create Statement Object // Statement stmt = con.createStatement();
        // Execute Query // ResultSet rs = stmt.executeQuery("SELECT * FROM EMP");
        // Close Connection Object // con.close()

        // Configure Port
        System.out.print("Set port number (1024~65536): ");
        int port = scanner.nextInt();

        // Create ServerSocket and start accepting client requests
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Starting TM2N");
//        ExecutorService executorService = Executors.newFixedThreadPool(1000);

        // connection thread
        while (true)
            new ClientHandler(serverSocket.accept()).start();
    }

// I am sorry... I swear i won't do this kind of commit from now on... this is part of the challenge now
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Read request from client
//            try {
//                String request = in.readLine();
//                String[] parsedRequest = request.split(" ");
//                switch (parsedRequest[0]) {
//                    case "GET":
//                        // getter
//                        break;
//                    case "POST":
//                        // setter
//                        break;
//                    case "PUT":
//                        //
//                        break;
//                    case "DELETE":
//                        //
//                        break;
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }

            try {
                out.close();
                in.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}



// Web operations that need the backend -> compuation is not required.
// DB access (or processed DB query)