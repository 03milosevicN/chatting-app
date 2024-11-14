package org.example.Models;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private Socket clientSocket;
    private PrintWriter out;
    private Scanner in;


    public void connect(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);

        System.out.println("Connected to " + ip + ":" + port);

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new Scanner(clientSocket.getInputStream());

        while (in.hasNextLine()) {
            String serverMessage = in.nextLine();
            System.out.println("@Server: " + serverMessage);
        }

    }

    public void sendMessage() throws IOException {

        Scanner consoleInput = new Scanner(System.in);
        String userInput;

        while (true) {
            System.out.println("Enter message: ");
            userInput = consoleInput.nextLine();
            out.println(userInput);

            if(userInput.equals("quit-server")) {
                System.out.println("Goodbye!");
                clientSocket.close();
                break;
            }

        }
    }

}
