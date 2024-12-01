package org.example.Model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable {

    protected int CLIENT_PORT;
    public String CLIENT_NAME;

    public String getClientName() {
        return CLIENT_NAME;
    }

    public void connect() throws Exception {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter port: ");
        CLIENT_PORT = s.nextInt();

        s.nextLine();

        System.out.println("Enter name: ");
        CLIENT_NAME = s.nextLine();

        Socket clientSocket = new Socket("127.0.0.1", CLIENT_PORT);

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

        // send client name to server:
        out.println(CLIENT_NAME);

        String write, serverResponse;

        while(true) {

            System.out.println(CLIENT_NAME + ": ");
            write = stdIn.readLine();

            if (write.equalsIgnoreCase("exit"))  {
                System.out.println("Exiting @Client: " + CLIENT_NAME);
                break;
            }

            out.println(write);
            serverResponse = in.readLine();
            System.out.println(serverResponse);

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
