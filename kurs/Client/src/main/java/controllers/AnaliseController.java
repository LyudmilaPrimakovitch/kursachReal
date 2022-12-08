package controllers;

import StorOrg.Products;
import WorkC.Connection;
import checks.Allerts;
import checks.CW;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AnaliseController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button sortV;

    @FXML
    private Button best;

    @FXML
    private Button worst;


    @FXML
    void sortV(ActionEvent event) throws IOException {
        Connection.client.sendMessage("sortV");
        CW.changeWindow(getClass(), sortV, "showClients.fxml", "", false);
    }

    @FXML
    void worst(ActionEvent event) throws IOException {
        Connection.client.sendMessage("worst");
        CW.changeWindow(getClass(), worst, "showClients.fxml", "", false);
    }


    @FXML
    void best(ActionEvent event) throws IOException {
        Connection.client.sendMessage("best");
        CW.changeWindow(getClass(), best, "showClients.fxml", "", false);
    }

    @FXML
    void backToMenuAdmin(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/menuAdmin.fxml"));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene((root)));
        stage.show();
    }


    @FXML
    void initialize() {

    }
}
