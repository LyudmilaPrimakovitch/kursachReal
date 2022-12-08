package controllers;

import StorOrg.Clients;
import WorkC.Connection;
import checks.Allerts;
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
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShowClientsController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Clients> infTable;

    @FXML
    private TextField login;

    @FXML
    private TableColumn<Clients, String> logColumn;

    @FXML
    private TableColumn<Clients, String> firmColumn;

    @FXML
    private TableColumn<Clients, String> mailColumn;

    @FXML
    private TableColumn<Clients, String> effColumn;

    @FXML
    private Button searchButton;

    ObservableList<Clients> clients = FXCollections.observableArrayList();

    @FXML
    void searchLog(ActionEvent event) {
        if(login.getText() == "")
            Allerts.showAlertWithNullInput();
        else {
            Connection.client.sendMessage("findClients");
            Clients c = new Clients();
            c.setLogin(login.getText());
            Connection.client.sendObject(c);
            try {
                clients.clear();
                ArrayList<Clients> clientsList = (ArrayList<Clients>) Connection.client.readObject();
                System.out.println(clientsList);
                clients.addAll(clients);
                infTable.setItems(clients);
            } catch (Exception ex) {
            }
        }
    }

    @FXML
    void backToMain(ActionEvent event) throws IOException {
            CW.changeWindow(getClass(), backButton, "Analise.fxml", "", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getLogin()));
        firmColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getCompanyName()));
        mailColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getEmail()));
        effColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getAverageV()));
        infTable.setItems(getClient());
    }

    private ObservableList<Clients> getClient() {
        ObservableList<Clients> clientsObservableList = FXCollections.observableArrayList();
        ArrayList<Clients> clients1 = (ArrayList<Clients>) Connection.client.readObject();
        System.out.println(clients1);
        clientsObservableList.addAll(clients1);
        infTable.setItems(clientsObservableList);
        return clientsObservableList;
    }
}
