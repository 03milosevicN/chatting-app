package org.example.Core;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket socket;


    public void start(int port) throws Exception {

        socket = new ServerSocket(port);

        System.out.println("Server started on port: " + port);
        System.out.println("Hello!");

        while (true) {
            Socket clientSocket = socket.accept();
            System.out.println("Client connected.");

            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("Welcome, Client! :]");
        }

    }


    public void stop() throws Exception {
        socket.close();
    }


}
