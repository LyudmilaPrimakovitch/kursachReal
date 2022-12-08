package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import WorkC.Connection;
import StorOrg.Worker;
import checks.Allerts;

public class DeleteWorkerController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button deleteButton;

    @FXML
    private TextField idworker;

    @FXML
    void deleteWork(ActionEvent event) {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            Worker worker = new Worker();
            worker.setLogin(idworker.getText());
            Connection.client.sendMessage("delWorker");
            Connection.client.sendObject(worker);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch (IOException ex) {
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при удалении работника"))
                Allerts.showAlertWithData();
            else {
                Allerts.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return idworker.getText().equals("");
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
