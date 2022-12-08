package controllers;

import StorOrg.Products;
import StorOrg.TTNs;
import WorkC.Connection;
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

public class GetTTNController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button registrationButton;

    @FXML
    private Button rejectButton;



    @FXML
    private TextField date;

    @FXML
    private TextField id;

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/menuWorker.fxml"));

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
    void bringTTN(ActionEvent event) throws IOException {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            TTNs ttNs = new TTNs();
            ttNs.setWorkerid(Connection.id);
            ttNs.setTime(date.getText());
            ttNs.setId(Integer.parseInt(id.getText()));
            ttNs.setStatus("принято");
            Connection.client.sendMessage("updateTTN");
            Connection.client.sendObject(ttNs);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при оформлении"))
                Allerts.showAlertWithData();
            else {
                Allerts.correctOperation();
            }
        }
    }

    @FXML
    void rejectTTN(ActionEvent event) throws IOException {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            TTNs ttNs = new TTNs();
            ttNs.setWorkerid(Connection.id);
            ttNs.setTime(date.getText());
            ttNs.setId(Integer.parseInt(id.getText()));
            ttNs.setStatus("брак");
            Connection.client.sendMessage("rejectTTN");
            Connection.client.sendObject(ttNs);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при оформлении"))
                Allerts.showAlertWithData();
            else {
                Allerts.correctOperation();
            }
        }
    }
    private boolean checkInput() {
        try {
            return id.getText().equals("") || date.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

}
