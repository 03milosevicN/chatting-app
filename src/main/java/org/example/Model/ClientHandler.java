package org.example.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    final Socket establishClientSocket;
    private final String clientName;
    private Server server;
    private BufferedReader in;
    private PrintStream out;

    public ClientHandler(Socket establishClientSocket, String clientName, Server server) {
        this.establishClientSocket = establishClientSocket;
        this.clientName = clientName;
        this.server = server;
    }

    @Override
    public void run() {
        try {
            out = new PrintStream(establishClientSocket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(establishClientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String clientMessage = in.readLine();

                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client " + clientName + " disconnected.");
                    break;
                }

                System.out.println(clientName + ": " + clientMessage);

                server.broadcastMessage(clientName + ":" + clientMessage, this);

//                System.out.println("@Server: ");
//                String response = stdIn.readLine();
//                out.println(response);
            }

        }
        catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

}
