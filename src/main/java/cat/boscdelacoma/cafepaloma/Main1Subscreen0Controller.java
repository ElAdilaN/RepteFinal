/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.boscdelacoma.cafepaloma;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author noureddin
 */
public class Main1Subscreen0Controller implements Initializable {

    @FXML
    private Label label;
    @FXML
    private BorderPane mainPane1;

    @FXML
    private void handleButtonMain1sub1ActionRefrescos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main1Subscreen1Refrescos.fxml"));
            Pane view = loader.load();
            mainPane1.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the FXML file.");
        }
    }

    @FXML
    private void handleButtonMain1sub1ActionCafeteria(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main1Subscreen1Cafeteria.fxml"));
            Pane view = loader.load();
            mainPane1.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the FXML file.");
        }
    }

    @FXML
    private void handleButtonMain1sub1ActionEntrepans(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main1Subscreen1Entrepans.fxml"));
            Pane view = loader.load();
            mainPane1.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the FXML file.");
        }
    }

    @FXML
    private void handleButtonMain1sub1ActionProducts(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Main1Subscreen1Products.fxml"));
            Pane view = loader.load();
            mainPane1.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load the FXML file.");
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
// TODO
    }
}
