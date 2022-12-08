package controllers;

import StorOrg.Clients;
import WorkC.Connection;
import checks.Allerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BlokClientController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idclient;

    @FXML
    void deleteClient(ActionEvent event) {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            Clients clients = new Clients();
            clients.setLogin(idclient.getText());
            Connection.client.sendMessage("delClient");
            Connection.client.sendObject(clients);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch (IOException ex) {
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при блокировке"))
                Allerts.showAlertWithData();
            else {
                Allerts.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return idclient.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {

    }
}
