/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.boscdelacoma.cafepaloma;

import cat.boscdelacoma.cafepaloma.data.AppQuery;
import cat.boscdelacoma.cafepaloma.model.Cafeteria;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;

/**
 * FXML Controller class
 *
 * @author noureddin
 */
public class Main1Subscreen1CafeteriaController implements Initializable {

        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showCafeteria();
        btnCafeteriaUpdate.setDisable(true);
        btnCafeteriaDelete.setDisable(true);
        fieldCafeteriaSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            filterData(newValue);
        });
    }

    @FXML
    public TextField fieldCafeteriaName;
    @FXML
    public TextField fieldCafeteriaPrice;
    @FXML
    public TextField fieldCafeteriaSize;
    @FXML
    public TextField fieldCafeteriaSearch;


    @FXML
    public Button btnCafeteriaNew;

    @FXML
    public Button btnCafeteriaSave;

    @FXML
    public Button btnCafeteriaUpdate;

    @FXML
    public Button btnCafeteriaDelete;

    @FXML
    public TableView<Cafeteria> tableViewCafeteria;

    @FXML
    public TableColumn<Cafeteria, Integer> colIdCafeteria;

    @FXML
    public TableColumn<Cafeteria, String> colNameCafeteria;

    @FXML
    public TableColumn<Cafeteria, Double> colPriceCafeteria;

    @FXML
    public TableColumn<Cafeteria, String> colSizeCafeteria;

    @FXML
    private void addCafeteria() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Confirmation");
        dialog.setHeaderText("are you sure , u wanna add or save ");
        dialog.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Name:" + fieldCafeteriaName.getText() + "  " + fieldCafeteriaPrice.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            cat.boscdelacoma.cafepaloma.model.Cafeteria cafeteria = new cat.boscdelacoma.cafepaloma.model.Cafeteria( fieldCafeteriaName.getText(), Double.parseDouble(fieldCafeteriaPrice.getText()), fieldCafeteriaSize.getText());
            cat.boscdelacoma.cafepaloma.data.AppQuery query = new AppQuery();
            query.addCafeteria(cafeteria);
        }

    }

    private Cafeteria cafeteria;

    @FXML
    private void showCafeteria() {

        AppQuery query = new AppQuery();

        ObservableList<Cafeteria> list = query.getCafeteriaList();
        colIdCafeteria.setCellValueFactory(new PropertyValueFactory<Cafeteria, Integer>("id"));
        colNameCafeteria.setCellValueFactory(new PropertyValueFactory<Cafeteria, String>("Name"));
        colPriceCafeteria.setCellValueFactory(new PropertyValueFactory<Cafeteria, Double>("Price"));
        colSizeCafeteria.setCellValueFactory(new PropertyValueFactory<Cafeteria, String>("Size"));

        tableViewCafeteria.setItems(list);

    }

    @FXML
    public void mouseClicked(MouseEvent e) {
        try {

            Cafeteria cafeteria = tableViewCafeteria.getSelectionModel().getSelectedItem();
            cafeteria = new Cafeteria(cafeteria.getId(), cafeteria.getName(), cafeteria.getPrice(), cafeteria.getSize());
            this.cafeteria = cafeteria;
            fieldCafeteriaName.setText(cafeteria.getName());
            fieldCafeteriaPrice.setText(Double.toString(cafeteria.getPrice()));
            fieldCafeteriaSize.setText(cafeteria.getSize());

            btnCafeteriaUpdate.setDisable(false);
            btnCafeteriaDelete.setDisable(false);
            btnCafeteriaSave.setDisable(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void UpdateCafeteria() {
        try {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("update Confirmation");
            dialog.setHeaderText("are you sure , u wanna update ");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldCafeteriaName.getText() + "  " + fieldCafeteriaPrice.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
            cat.boscdelacoma.cafepaloma.model.Cafeteria cafeteria = new cat.boscdelacoma.cafepaloma.model.Cafeteria(this.cafeteria.getId(), fieldCafeteriaName.getText(), Double.parseDouble(fieldCafeteriaPrice.getText()), fieldCafeteriaSize.getText());
                query.UpdateCafeteria(cafeteria);
                showCafeteria();
                clearField();
                btnCafeteriaUpdate.setDisable(true);
                btnCafeteriaDelete.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void DeleteCafeteria() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("delete Confirmation");
            dialog.setHeaderText("are you sure , u wanna delete ");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldCafeteriaName.getText() + "  " + fieldCafeteriaPrice.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
            cat.boscdelacoma.cafepaloma.model.Cafeteria cafeteria = new cat.boscdelacoma.cafepaloma.model.Cafeteria( this.cafeteria.getId(),fieldCafeteriaName.getText(), Double.parseDouble(fieldCafeteriaPrice.getText()), fieldCafeteriaSize.getText());
                query.DeleteCafeteria(cafeteria);
                showCafeteria();
                clearField();
                btnCafeteriaUpdate.setDisable(true);
                btnCafeteriaDelete.setDisable(true);
            }
            
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearField() {
        fieldCafeteriaName.setText("");
        fieldCafeteriaPrice.setText("");
        fieldCafeteriaSize.setText("");
    }

    @FXML
    private void clickNew() {
        btnCafeteriaUpdate.setDisable(true);
        btnCafeteriaDelete.setDisable(true);
        clearField();
        btnCafeteriaSave.setDisable(false);
    }

    private void filterData(String searchName) {
        ObservableList<Cafeteria> filterData = FXCollections.observableArrayList();
        cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
        ObservableList<Cafeteria> list = query.getCafeteriaList();
        for (Cafeteria cafeteria : list) {
            if (cafeteria.getName().toLowerCase().contains(searchName.toLowerCase())
                    || Double.toString(cafeteria.getPrice()).toLowerCase().contains(searchName.toLowerCase())
                    || cafeteria.getSize().toLowerCase().contains(searchName.toLowerCase())) {
                filterData.add(cafeteria);
            }
        }

        tableViewCafeteria.setItems(filterData);

    }    
    
}
