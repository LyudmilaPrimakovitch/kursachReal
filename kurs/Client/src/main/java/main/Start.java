package main;


import WorkC.Client;
import WorkC.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Start extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Start.class.getResource("/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 400);
        stage.setTitle("Autorization");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) throws IOException {
        Connection.client = new Client("127.0.0.2", "9006");
       launch();
    }
}
