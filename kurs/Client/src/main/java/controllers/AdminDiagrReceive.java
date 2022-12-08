package controllers;

import WorkC.Connection;
import checks.Allerts;
import checks.CW;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminDiagrReceive{
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private Button backButton;

        @FXML
        private Button saveButton;

        @FXML
        private PieChart storDiagram;

        @FXML
        void backToMain(ActionEvent event) throws IOException {
            CW.changeWindow(getClass(), backButton, "menuAdmin.fxml", "", false);
        }

        @FXML
        void save(ActionEvent event) throws IOException {
            Connection.client.sendMessage("writeReceiveReport");

            String mes = "";
            try {
                mes = Connection.client.readMessage();
            } catch(IOException ex){
                System.out.println("Ничего нет");
            }
            System.out.println(mes);
            if (mes.equals("Ошибка при составлении отчета"))
                Allerts.showAlertWithData();
            else {
                Allerts.correctOperation();
            }
        }

        @FXML
        void initialize() {

            ArrayList<AbstractMap.SimpleEntry<String, Integer>> data
                    = (ArrayList<AbstractMap.SimpleEntry<String, Integer>>) Connection.client.readObject();

            for (AbstractMap.SimpleEntry<String, Integer> point: data) {
                storDiagram.getData().add(new PieChart.Data(point.getKey(), point.getValue()));
            }

            storDiagram.setLegendSide(Side.LEFT);
        }
}