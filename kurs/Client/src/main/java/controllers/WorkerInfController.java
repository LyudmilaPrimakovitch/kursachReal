package controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import StorOrg.Worker;
import WorkC.Connection;
import checks.CW;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WorkerInfController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Worker> infTable;

    @FXML
    private TableColumn<Worker, String> loginColumn;

    @FXML
    private TableColumn<Worker, String> firstnameColumn;

    @FXML
    private TableColumn<Worker, String> lastnameColumn;

    @FXML
    private TableColumn<Worker, String> categoryColumn;

    @FXML
    private TableColumn<Worker, String> storageColumn;

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        CW.changeWindow(getClass(), backButton, "menuWorker.fxml", "", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLogin()));
        firstnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getFirstname()));
        lastnameColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLastname()));
        categoryColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCategory()));
        storageColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getStorage()));
        infTable.setItems(getWorker());
    }

    private ObservableList<Worker> getWorker() {
        ObservableList<Worker> workerList = FXCollections.observableArrayList();
        ArrayList<Worker> teachers = (ArrayList<Worker>) Connection.client.readObject();
        System.out.println(teachers);
        workerList.addAll(teachers);
        infTable.setItems(workerList);
        return workerList;
    }
}
