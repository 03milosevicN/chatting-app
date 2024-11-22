package org.example.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    protected int CLIENT_PORT;

    public void connect() throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter port: ");
        CLIENT_PORT = s.nextInt();

        Socket clientSocket = new Socket("127.0.0.1", CLIENT_PORT);

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        String write, serverResponse;

        while(true) {

            System.out.println("@Client: ");
            write = stdIn.readLine();

            if (write.equalsIgnoreCase("exit"))  {
                System.out.println("Exiting @Client.");
                break;
            }

            out.println(write);
            serverResponse = in.readLine();
            System.out.println("@Server:" + serverResponse);

        }

        out.close();
        in.close();
        stdIn.close();
        clientSocket.close();
        System.out.println("Client socket is closed.");
    }

    @Override
    public void run() {

        try {
            connect();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//        Client client = new Client();
//        Thread thread = new Thread(client);
//        thread.start();
//    }

}
