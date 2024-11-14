package org.example.Models;

public class ClientThread implements Runnable {

    private Client client;

    public ClientThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {

        try {
            client.connect("localhost", 8080);
            client.sendMessage();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
