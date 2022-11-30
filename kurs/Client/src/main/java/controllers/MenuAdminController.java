package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import WorkC.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import checks.CW;

public class MenuAdminController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button dbWorkButton;

    @FXML
    private Button clientBlok;

    @FXML
    private Button inventarization;

    @FXML
    private Button personalInfButton;

    @FXML
    private Button clientAnalise;

    @FXML
    private Button profitButton;



    @FXML
    void analise(ActionEvent event) throws IOException {
        Connection.client.sendMessage("analise");
        CW.changeWindow(getClass(), clientAnalise, "analiseClient.fxml", "", false);
    }

    @FXML
    void profit(ActionEvent event) throws IOException {
        Connection.client.sendMessage("getDiagrReceive");
        CW.changeWindow(getClass(), clientAnalise, "adminDiagrReceive.fxml", "", false);
    }

    @FXML
    void persInf(ActionEvent event) throws IOException {
        Connection.client.sendMessage("adminInf");
        CW.changeWindow(getClass(), personalInfButton, "adminInformation.fxml", "", false);
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
    void dbWork(ActionEvent event) {
        dbWorkButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/dbWork.fxml"));

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

//    @FXML
//    void showGrTimetable(ActionEvent event) throws IOException {
//        Connection.client.sendMessage("showGrTimetable");
//        CW.changeWindow(getClass(), showGrButton, "groupsTimetable.fxml", "", false);
//    }

//    @FXML
//    void showTTimetable(ActionEvent event) throws IOException {
//        Connection.client.sendMessage("showGrTeacher");
//        CW.changeWindow(getClass(), showTeachButton, "showTeachers.fxml", "", false);
//    }

    @FXML
    void initialize() {

    }
}
