package controllers;

import StorOrg.Worker;
import WorkC.Connection;
import StorOrg.Role;
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
import java.util.ResourceBundle;

public class MenuWorkerController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button personalInfButton;

    @FXML
    private Button TTNButton;

    @FXML
    private Button ReguestsButton;

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        CW.changeWindow(getClass(), backButton, "main.fxml", "", false);
    }

    @FXML
    void persInf(ActionEvent event) throws IOException {
        Connection.client.sendMessage("workerInf");
        Role r = new Role();
        r.setId(Connection.id);
        Connection.client.sendObject(r);
        CW.changeWindow(getClass(), personalInfButton, "workerInformation.fxml", "", false);
    }

    @FXML
    void setTTNButton(ActionEvent event) throws IOException {
        TTNButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/getTTN.fxml"));

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
    void showRequests(ActionEvent event) throws IOException {
        Connection.client.sendMessage("showRequests");
        Role r = new Role();
        r.setId(Connection.id);
        Connection.client.sendObject(r);
        CW.changeWindow(getClass(), ReguestsButton, "showTTNs.fxml", "", false);
    }


    @FXML
    void initialize() {

    }

}
