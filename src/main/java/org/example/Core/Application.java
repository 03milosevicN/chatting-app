package org.example.Core;

import org.example.Models.Client;
import org.example.Models.ClientThread;

public class Application {

    public static void main(String[] args) {

        // Main classes:
        Server server = new Server();
        Client client = new Client();


        // Threads of main classes:
        Thread serverThread = new Thread(new ServerThread(server));
        Thread clientThread = new Thread(new ClientThread(client));


        serverThread.start();

        // Give server time to start before client connects:
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        clientThread.start();
    }

}
