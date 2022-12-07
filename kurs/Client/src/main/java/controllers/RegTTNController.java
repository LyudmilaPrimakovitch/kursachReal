package controllers;

import StorOrg.Products;
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

public class RegTTNController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private Button registrationButton;

    @FXML
    private ComboBox<String> storageList = new ComboBox();

    @FXML
    private ComboBox<String> productList = new ComboBox();

    @FXML
    private TextField name;

    @FXML
    private TextField size;

    @FXML
    void backToMain(ActionEvent event) {
        backButton.getScene().getWindow().hide();

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

    @FXML
    void registrationTTN(ActionEvent event) throws IOException {
        if (checkInput())
           Allerts.showAlertWithNullInput();
        else {
            Products products = new Products();
            products.setIdclient(Connection.id);
            products.setStorage(storageList.getValue());
            products.setTypeProd(productList.getValue());
            products.setBalance(20);
            products.setName(name.getText());
            products.setV(Integer.parseInt(size.getText()));
            Connection.client.sendMessage("regTTN");
            Connection.client.sendObject(products);
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
            return storageList.getValue().equals("") || productList.getValue().equals("")||
                    name.getText().equals("")||size.getText().equals("");
        }
        catch (Exception e) {
            System.out.println("Error");
            return true;
        }
    }

    @FXML
    void initialize() {
        storageList.getItems().addAll(
                "Открытый",
                "Крытый",
                "Рефрижераторный",
                "Закрытый",
                "Стеллажный"
        );
        productList.getItems().addAll(
                "Молочка",
                "Древесина",
                "Мебель",
                "Зерно",
                "Фрукты/овощи",
                "Ящики",
                "Иное"
        );
    }
}
