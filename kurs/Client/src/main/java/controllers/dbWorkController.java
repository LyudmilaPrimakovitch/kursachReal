package controllers;

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
import checks.CW;

public class dbWorkController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button workerButton;

    @FXML
    private Button chProdButton;

    @FXML
    private Button chWorkerButton;

    @FXML
    private Button prodButton;

    @FXML
    private Button delWorkerButton;

    @FXML
    void addProd(ActionEvent event) throws IOException {
        CW.changeWindow(getClass(), prodButton, "addProd.fxml", "AddProd", true);
    }

    @FXML
    void addWorker(ActionEvent event) {
        workerButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/addWorker.fxml"));

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
    void changeProd(ActionEvent event) {

    }

    @FXML
    void changeWorker(ActionEvent event) throws IOException {
        CW.changeWindow(getClass(), chWorkerButton, "changeTeacher.fxml", "", true);
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
    void deleteWorker(ActionEvent event) throws IOException {
        CW.changeWindow(getClass(), delWorkerButton, "deleteWork.fxml", "", true);
    }

    @FXML
    void initialize() {

    }
}
