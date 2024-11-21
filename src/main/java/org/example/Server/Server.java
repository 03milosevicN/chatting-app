package org.example.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static int SERVER_PORT;


    public static void start() throws Exception {
        Scanner s = new Scanner(System.in);

        System.out.println("Enter port: ");
        SERVER_PORT = s.nextInt();

        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

        System.out.println("Server listening on port " + SERVER_PORT);
        System.out.println("Waiting for connections...");

        Socket establishClientSocket = serverSocket.accept();

        if (establishClientSocket != null) {
            System.out.println("Client connected.");

            PrintStream out = new PrintStream(establishClientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(establishClientSocket.getInputStream()));
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));


            while (true) {

                String write = in.readLine();
                if (write.equalsIgnoreCase("exit")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                System.out.println("@Client: " + write);

                System.out.println("@Server: ");
                String response = input.readLine();
                out.println(response);
            }


            establishClientSocket.close();
            serverSocket.close();
            System.out.println("Closing sockets..");
            System.out.println("Shutting server down..");
        }
        else {
            System.out.println("Connection not established!");
        }
    }


    // No threads at the moment, must run from its own main function:
    public static void main(String[] args) {
        try {
            start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
