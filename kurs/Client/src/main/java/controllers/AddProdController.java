package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import WorkC.Connection;
import checks.CW;
import checks.Allerts;
import StorOrg.Products;
public class AddProdController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button registrationButton;

    @FXML
    private ComboBox<String> storageList = new ComboBox<>();

    @FXML
    private ComboBox<String> productList = new ComboBox<>();

    @FXML
    void registrationProduct(ActionEvent event) {
        if (checkInput())
            Allerts.showAlertWithNullInput();
        else {
            Products product = new Products();
            product.setStorage(storageList.getValue());
            product.setTypeProd(productList.getValue());
            Connection.client.sendMessage("addProd");
            Connection.client.sendObject(product);
            System.out.println("Запись отправлена");

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch(IOException ex){
                System.out.println("Error in reading");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при записи курса"))
                Allerts.showAlertWithData();
            else {
                Allerts.correctOperation();
            }
        }
    }

    private boolean checkInput() {
        try {
            return storageList.getValue().equals("") || productList.getValue().equals("");
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
