package org.example.Core;

import org.example.Model.Client;

public class Application {

    public static void main(String[] args) {

        try {
            Client client = new Client();
            Thread thread = new Thread(client);
            thread.start();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

}
