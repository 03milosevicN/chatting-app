package org.example.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    protected int SERVER_PORT;

    public void startServer() throws Exception {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter port: ");
        SERVER_PORT = s.nextInt();

        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        System.out.println("Server listening on port " + SERVER_PORT);
        System.out.println("Waiting for connections...");

        while (true) {
            Socket establishClientSocket = serverSocket.accept();
            System.out.println("Established connection from " + establishClientSocket.getInetAddress());

            //ClientHandler is a Runnable that handles clients:
            ClientHandler clientHandler = new ClientHandler(establishClientSocket);
            new Thread(clientHandler).start();
        }

    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.startServer();
    }

}

