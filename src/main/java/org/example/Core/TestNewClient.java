// Temporary class for testing a 2nd client. Not to be commited!

package org.example.Core;

import org.example.Model.Client;

public class TestNewClient {

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
