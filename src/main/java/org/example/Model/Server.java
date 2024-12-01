package org.example.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.*;

public class Server {

    protected int SERVER_PORT;

    // this stores all client handlers:
    private final List<ClientHandler> clients = new ArrayList<>();



    public void startServer() throws Exception {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter port: ");
        SERVER_PORT = s.nextInt();

        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        System.out.println("Server listening on port " + SERVER_PORT);
        System.out.println("Waiting for connections...");

        while (true) {
            Socket establishClientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(establishClientSocket.getInputStream()));
            String clientName = in.readLine();

            System.out.println("Established connection from " + establishClientSocket.getInetAddress() + " named: " + clientName);

            //ClientHandler is a Runnable that handles clients:
            ClientHandler clientHandler = new ClientHandler(establishClientSocket, clientName, this);
            synchronized (clients) {
                clients.add(clientHandler);
            }
            new Thread(clientHandler, clientName).start();
        }

    }

    //function for message broadcasting:
    public void broadcastMessage(String message, ClientHandler sender) {
        synchronized (clients) {
            for (ClientHandler clientHandler : clients) {
                if (clientHandler != sender) {
                    clientHandler.sendMessage(message);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.startServer();
    }

}

