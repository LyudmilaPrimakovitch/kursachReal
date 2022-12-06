package controllers;

import WorkC.Connection;
import StorOrg.Worker;
import checks.Allerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangeWorkerController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField idCh;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button backButton;

    @FXML
    private Button changeButton;

    @FXML
    private ComboBox<String> category = new ComboBox<>();

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    void changeWorker(ActionEvent event) {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            Worker worker = new Worker();
            worker.setLastlogin(idCh.getText());
            worker.setFirstname(firstName.getText());
            worker.setLastname(lastName.getText());
            worker.setLogin(login.getText());
            worker.setPassword(password.getText());
            worker.setCategory(category.getValue());
            Connection.client.sendMessage("changeWorker");
            Connection.client.sendObject(worker);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            if (mes.equals("Incorrect Data"))
                Allerts.showAlertWithExistLogin();
            else {
                Allerts.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return  idCh.getText().equals("") || firstName.getText().equals("") || lastName.getText().equals("") ||
                    login.getText().equals("") || password.getText().equals("") ||
                    category.getValue().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {
        category.getItems().addAll(
                "Первый",
                "Второй",
                "Третий",
                "Четвертый",
                "Пятый"
        );
    }
}
