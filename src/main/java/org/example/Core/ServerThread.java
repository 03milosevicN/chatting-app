package org.example.Core;

public class ServerThread implements Runnable {

    private Server server;

    public ServerThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {

        try {
            server.start(8080);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }

}