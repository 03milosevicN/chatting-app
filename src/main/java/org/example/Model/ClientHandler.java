package org.example.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ClientHandler implements Runnable {

    final Socket establishClientSocket;

    public ClientHandler(Socket establishClientSocket) {
        this.establishClientSocket = establishClientSocket;
    }


    @Override
    public void run() {
        try {
            PrintStream out = new PrintStream(establishClientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(establishClientSocket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String clientMessage = in.readLine();

                if (clientMessage == null || clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                System.out.println("@Client: " + clientMessage);
                System.out.println("@Server: ");
                String response = stdIn.readLine();
                out.println(response);
            }

        }
        catch (IOException e) {
            System.out.println("Error handling client: " + e.getMessage());
        }

    }

}
