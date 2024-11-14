package org.example.Core;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler extends Thread {

    private Socket clientSocket;
    private PrintWriter out;
    private Scanner in;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    // Thread logic:
    @Override
    public void run() {

        try {

            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new Scanner(clientSocket.getInputStream());

            String inputLine;
            while ((inputLine = in.nextLine()) != null) {

                out.println("Received: " + inputLine);

            }

            in.close();
            out.close();
            clientSocket.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}
