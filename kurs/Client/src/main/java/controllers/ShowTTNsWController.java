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

public class ShowTTNsWController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<TTNs> infTable;


    @FXML
    private TableColumn<TTNs, Integer> idttn;

    @FXML
    private TableColumn<TTNs, String> prodTcol;

    @FXML
    private TableColumn<TTNs, String> prodCol;

    @FXML
    private TableColumn<TTNs, Integer> VCol;

    @FXML
    private TableColumn<TTNs, Integer> clientCol;

    @FXML
    private Button searchButton;

    ObservableList<TTNs> clients = FXCollections.observableArrayList();


    @FXML
    void backToMain(ActionEvent event) throws IOException {
        CW.changeWindow(getClass(), backButton, "menuWorker.fxml", "", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        idttn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getId()));
        prodTcol.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getTypeProduct()));
        prodCol.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getProdName()));
        VCol.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getSize()));
        clientCol.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getIdClient()));
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
