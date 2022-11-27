package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import WorkC.Connection;
import StorOrg.Role;
import StorOrg.Clients;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import checks.Allerts;


public class RegistrationController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField login;

    @FXML
    private TextField password;

    @FXML
    private Button registrationButton;


    @FXML
    void initialize() {

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
    void registrationStudent(ActionEvent event) throws IOException {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            Clients client = new Clients();
            client.setCompanyName(firstName.getText());
            client.setEmail(lastName.getText());
            client.setLogin(login.getText());
            client.setPassword(password.getText());
            Connection.client.sendMessage("registrationClient");
            Connection.client.sendObject(client);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            if (mes.equals("This user is already existed"))
                Allerts.showAlertWithExistLogin();
            else {
                Role r = (Role) Connection.client.readObject();
                Connection.id = r.getId();
                Connection.role = r.getRole();
                registrationButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/menuClient.fxml"));

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
        }
    }

    private boolean checkInput() {
        try {
            return firstName.getText().equals("") || lastName.getText().equals("") ||
                    login.getText().equals("") || password.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }
}
