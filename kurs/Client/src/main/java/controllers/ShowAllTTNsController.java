package controllers;

import StorOrg.TTNs;
import WorkC.Connection;
import checks.CW;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowAllTTNsController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<TTNs> infTable;


    @FXML
    private TableColumn<TTNs, Integer> id;

    @FXML
    private TableColumn<TTNs, String> prodColumn;

    @FXML
    private TableColumn<TTNs, Integer> sizeCol;

    @FXML
    private TableColumn<TTNs, String> statusCol;
    @FXML
    private TableColumn<TTNs, String> dateCol;

    ObservableList<TTNs> ttns = FXCollections.observableArrayList();


    @FXML
    void backToMain(ActionEvent event) throws IOException {
        CW.changeWindow(getClass(), backButton, "menuAdmin.fxml", "", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        id.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getId()));
        prodColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getProdName()));
        sizeCol.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSize()));
        statusCol.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getStatus()));
        dateCol.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getTime()));
        infTable.setItems(getTTN());
    }

    private ObservableList<TTNs> getTTN() {
        ObservableList<TTNs> ttnsList = FXCollections.observableArrayList();
        ArrayList<TTNs> listTTN = (ArrayList<TTNs>) Connection.client.readObject();
        System.out.println(listTTN);
        ttnsList.addAll(listTTN);
        infTable.setItems(ttnsList);
        return ttnsList;
    }
}
