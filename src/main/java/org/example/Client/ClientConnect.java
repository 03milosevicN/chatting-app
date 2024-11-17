package org.example.Client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static org.example.Server.Server.SERVER_PORT;

public class ClientConnect {

    public static int CLIENT_PORT;

    public static void connect() throws Exception {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter port: ");
        CLIENT_PORT = s.nextInt();

        Socket clientSocket = new Socket("127.0.0.1", CLIENT_PORT);


        System.out.println("Connection with server @ " + SERVER_PORT + "established.");

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String write, serverResponse;

            while (true) {
                System.out.println("@Client: ");
                write = input.readLine();

                if (write.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnected from server.");
                    break;
                }

                out.println(write);

                serverResponse = in.readLine();
                System.out.println("@Server:" + serverResponse);
            }

            in.close();
            out.close();
            input.close();
            clientSocket.close();
            System.out.println("Client socket closed.");

    }

    // No threads at the moment, must run from its own main function:
    public static void main(String[] args) {
        try {
            connect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
