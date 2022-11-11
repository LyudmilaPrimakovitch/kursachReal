package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import WorkC.Client;
import WorkC.Connection;
import StorOrg.Authorization;
import StorOrg.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import checks.Allerts;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login;

    @FXML
    private PasswordField password;

    @FXML
    private Button enterButton;

    @FXML
    private Button registrationButton;

    @FXML
    void authorization(ActionEvent event) throws IOException {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            Connection.client.sendMessage("authorization");
            Authorization auth = new Authorization();
            auth.setLogin(login.getText());
            auth.setPassword(password.getText());
            Connection.client.sendObject(auth);

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch (IOException ex) {
                System.out.println("Error in reading");
            }
            if (mes.equals("There is no data!"))
                Allerts.showAlertWithNoLogin();
            else {
                Role r = (Role) Connection.client.readObject();
                Connection.id = r.getId();
                Connection.role = r.getRole();
                enterButton.getScene().getWindow().hide();
                System.out.println(Connection.role);
                FXMLLoader loader = new FXMLLoader();

                if (Objects.equals(Connection.role, "client")) {
                    System.out.println("Окно поставщика");
                    loader.setLocation(getClass().getResource("/menuClient.fxml"));
                }
                else if(Objects.equals(Connection.role, "worker"))
                    loader.setLocation(getClass().getResource("/menuWorker.fxml"));
                else
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
        }
    }

    @FXML
    void registration(ActionEvent event) {

    }

    @FXML
    void initialize() {
        registrationButton.setOnAction(event -> {
            registrationButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/registration.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene((root)));
            stage.show();
        });
    }

    private boolean checkInput() {
        try {
            return login.getText().equals("") || password.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }
}