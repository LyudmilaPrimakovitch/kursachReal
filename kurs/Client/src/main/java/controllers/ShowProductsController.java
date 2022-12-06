package controllers;

import checks.Allerts;
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
import java.util.logging.Level;
import java.util.logging.Logger;

import StorOrg.Products;
import checks.CW;
import WorkC.Connection;

public class ShowProductsController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backButton;

    @FXML
    private TableView<Products> infTable;

    @FXML
    private TableColumn<Products, String> storageColumn;

    @FXML
    private TableColumn<Products, String> prodColumn;

    @FXML
    private Button searchButton;

    @FXML
    private TextField storage;

    ObservableList<Products> prodList = FXCollections.observableArrayList();

    @FXML
    void searchProd(ActionEvent event) {
        if(storage.getText() == "")
            Allerts.showAlertWithNullInput();
        else {
            Connection.client.sendMessage("findProducts");
            Products c = new Products();
            c.setStorage(storage.getText());
            Connection.client.sendObject(c);
            try {
                prodList.clear();
                ArrayList<Products> products = (ArrayList<Products>) Connection.client.readObject();
                System.out.println(products);
                prodList.addAll(products);
                infTable.setItems(prodList);
            } catch (Exception ex) {
//                Logger.getLogger(Show.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void backToMain(ActionEvent event) throws IOException {
        if (Connection.role.equals("worker"))
            CW.changeWindow(getClass(), backButton, "menuWorker.fxml", "", false);
        else if(Connection.role.equals("client"))
            CW.changeWindow(getClass(), backButton, "menuClient.fxml", "", false);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        storageColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getStorage()));
        prodColumn.setCellValueFactory(field -> new SimpleObjectProperty<>(field.getValue().getTypeProd()));
        infTable.setItems(getWorker());
    }

    private ObservableList<Products> getWorker() {
        ObservableList<Products> productList = FXCollections.observableArrayList();
        ArrayList<Products> products = (ArrayList<Products>) Connection.client.readObject();
        System.out.println(products);
        productList.addAll(products);
        infTable.setItems(productList);
        return productList;
    }
}
