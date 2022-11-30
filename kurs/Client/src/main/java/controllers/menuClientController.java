package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import WorkC.Connection;
import StorOrg.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import checks.CW;

public class menuClientController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button regTTNButton;

    @FXML
    private Button personalInfButton;

    @FXML
    private Button changeLP;


    @FXML
    private Button showTTNButton;

    @FXML

    private Button prodButton;

    @FXML
    void persInf(ActionEvent event) throws IOException {
        Connection.client.sendMessage("clientInf");
        Role r = new Role();
        r.setId(Connection.id);
        Connection.client.sendObject(r);
        CW.changeWindow(getClass(), personalInfButton, "clientInformation.fxml", "", false);
    }

    @FXML
    void showProduction(ActionEvent event) throws IOException {
        Connection.client.sendMessage("showProduction");
        CW.changeWindow(getClass(), prodButton, "showProduction.fxml", "", false);
    }

    @FXML
    void showTTN(ActionEvent event) throws IOException {
        Connection.client.sendMessage("showTTN");
        CW.changeWindow(getClass(), showTTNButton, "showTTN.fxml", "", false);
    }

    @FXML
    void changelogpas(ActionEvent event) throws IOException {
        Connection.client.sendMessage("changelp");
        Role r = new Role();
        r.setId(Connection.id);
        Connection.client.sendObject(r);
        CW.changeWindow(getClass(), changeLP, "changelp.fxml", "", false);
    }

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/main.fxml"));

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
    void regTTN(ActionEvent event) {
        regTTNButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/regTTN.fxml"));

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
