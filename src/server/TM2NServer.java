package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Day 5 - studying http/2... i dunno if required but good read anyways
// Day 6 - Study interface & try implementing HTTPRequest
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
        // define thread pool size here and make sure you provide reason behind pool size


        //!!!!!!!! IMPORTANT
        // Not allowed to just start new thread for every connection
        // limit thread size to 16 - tomcat standard
        // TCP with or without TLS -> module for HTTP and HTTPS should be different -> HTTPS = HTTP + TLS
        // instead of new ClientHandler every serverSocket, add it to an array of new connection? List?
        // Iterate through new connection and handshake to establish connection
        // when to close connection with HTTP/2? omg 하아...
        while (true)
            new ClientHandler(serverSocket.accept()).start();
    }

    // interface doesn't work... i mean it's a good blueprint, but doesn't have any functionality
    // other than, of course, provide public final static variables...
    // can object be public sttic variable?
    // I think annotation will work
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            // HANDSHAKE REQUIRED
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