package controllers;

import WorkC.Connection;
import StorOrg.Worker;
import checks.Allerts;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddWorkerController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private Button backButton;

    @FXML
    private Button registrationButton;

    @FXML
    private ComboBox<String> category = new ComboBox();

    @FXML
    private ComboBox<String> storage = new ComboBox();

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

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

    @FXML
    void registrationWorker(ActionEvent event) {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            Worker worker = new Worker();
            worker.setFirstname(firstName.getText());
            worker.setLastname(lastName.getText());
            worker.setLogin(login.getText());
            worker.setPassword(password.getText());
            worker.setCategory(category.getValue());
            worker.setStorage(storage.getValue());
            Connection.client.sendMessage("registrationWorker");
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
            return firstName.getText().equals("") || lastName.getText().equals("") ||
                    login.getText().equals("") || password.getText().equals("") ||
                    category.getValue().equals("") || storage.getValue().equals("");
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
        storage.getItems().addAll(
                "Открытый",
                "Крытый",
                "Рефрижераторный",
                "Закрытый",
                "Стеллажный"
        );
    }
}
