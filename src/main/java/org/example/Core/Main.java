package org.example.Core;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import org.example.Model.Client;
import org.example.View.ClientView;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ClientView view = new ClientView();

        Scene scene = new Scene(view);

        primaryStage.setTitle("chatting-app");
        primaryStage.setScene(scene);
        primaryStage.setWidth(500);
        primaryStage.setHeight(500);
        primaryStage.show();
    }

    public static void main(String[] args) {

        try {
            Client client = new Client();
            Thread thread = new Thread(client);
            thread.start();
        }
        catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        launch(args);
    }

}
