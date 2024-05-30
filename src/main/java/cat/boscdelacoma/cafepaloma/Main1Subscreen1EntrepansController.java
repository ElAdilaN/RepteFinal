/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.boscdelacoma.cafepaloma;

import cat.boscdelacoma.cafepaloma.data.AppQuery;
import cat.boscdelacoma.cafepaloma.model.Entrepans;
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
public class Main1Subscreen1EntrepansController implements Initializable {

        /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showEntrepans();
        btnEntrepansUpdate.setDisable(true);
        btnEntrepansDelete.setDisable(true);
        fieldEntrepanSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            filterData(newValue);
        });
    }

    @FXML
    public TextField fieldEntrepanName;
    @FXML
    public TextField fieldEntrepanPrice;
    @FXML
    public TextField fieldEntrepanFilling;
    @FXML
    public TextField fieldEntrepanSearch;


    @FXML
    public Button btnEntrepansNew;

    @FXML
    public Button btnEntrepansSave;

    @FXML
    public Button btnEntrepansUpdate;

    @FXML
    public Button btnEntrepansDelete;

    @FXML
    public TableView<Entrepans> tableViewEntrepans;

    @FXML
    public TableColumn<Entrepans, Integer> colidEntrepans;

    @FXML
    public TableColumn<Entrepans, String> colNameEntrepans;

    @FXML
    public TableColumn<Entrepans, Double> colPriceEntrepans;

    @FXML
    public TableColumn<Entrepans, String> colFillingEntrepans;

    @FXML
    private void addEntrepans() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Confirmation");
        dialog.setHeaderText("are you sure , u wanna add or save ");
        dialog.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Name:" + fieldEntrepanName.getText() + "  " + fieldEntrepanPrice.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            cat.boscdelacoma.cafepaloma.model.Entrepans entrepan = new cat.boscdelacoma.cafepaloma.model.Entrepans( fieldEntrepanName.getText(), Double.parseDouble(fieldEntrepanPrice.getText()), fieldEntrepanFilling.getText());
            cat.boscdelacoma.cafepaloma.data.AppQuery query = new AppQuery();
            query.addEntrepans(entrepan);
        }

    }

    private Entrepans entrepan;

    @FXML
    private void showEntrepans() {

        cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();

        ObservableList<Entrepans> list = query.getEntrepansList();
        colidEntrepans.setCellValueFactory(new PropertyValueFactory<Entrepans, Integer>("id"));
        colNameEntrepans.setCellValueFactory(new PropertyValueFactory<Entrepans, String>("name"));
        colPriceEntrepans.setCellValueFactory(new PropertyValueFactory<Entrepans, Double>("price"));
        colFillingEntrepans.setCellValueFactory(new PropertyValueFactory<Entrepans, String>("filling"));

        tableViewEntrepans.setItems(list);

    }

    @FXML
    public void mouseClicked(MouseEvent e) {
        try {

            Entrepans entrepan = tableViewEntrepans.getSelectionModel().getSelectedItem();
            entrepan = new Entrepans(entrepan.getId(), entrepan.getName(), entrepan.getPrice(), entrepan.getFilling());
            this.entrepan = entrepan;
            fieldEntrepanName.setText(entrepan.getName());
            fieldEntrepanPrice.setText(Double.toString(entrepan.getPrice()));
            fieldEntrepanFilling.setText(entrepan.getFilling());

            btnEntrepansUpdate.setDisable(false);
            btnEntrepansDelete.setDisable(false);
            btnEntrepansSave.setDisable(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void UpdateEntrepans() {
        try {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("update Confirmation");
            dialog.setHeaderText("are you sure , u wanna update ");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldEntrepanName.getText() + "  " + fieldEntrepanPrice.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
            cat.boscdelacoma.cafepaloma.model.Entrepans entrepan = new cat.boscdelacoma.cafepaloma.model.Entrepans(this.entrepan.getId(), fieldEntrepanName.getText(), Double.parseDouble(fieldEntrepanPrice.getText()), fieldEntrepanFilling.getText());
                query.UpdateEntrepans(entrepan);
                showEntrepans();
                clearField();
                btnEntrepansUpdate.setDisable(true);
                btnEntrepansDelete.setDisable(true);
           
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void DeleteEntrepans() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("delete Confirmation");
            dialog.setHeaderText("are you sure , u wanna delete ");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldEntrepanName.getText() + "  " + fieldEntrepanPrice.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
            cat.boscdelacoma.cafepaloma.model.Entrepans entrepan = new cat.boscdelacoma.cafepaloma.model.Entrepans( this.entrepan.getId(), fieldEntrepanName.getText(), Double.parseDouble(fieldEntrepanPrice.getText()), fieldEntrepanFilling.getText());
                query.DeleteEntrepans(entrepan);
                showEntrepans();
                clearField();
                btnEntrepansUpdate.setDisable(true);
                btnEntrepansDelete.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearField() {
        fieldEntrepanName.setText("");
        fieldEntrepanPrice.setText("");
        fieldEntrepanFilling.setText("");
    }

    @FXML
    private void clickNew() {
        btnEntrepansUpdate.setDisable(true);
        btnEntrepansDelete.setDisable(true);
        clearField();
        btnEntrepansSave.setDisable(false);
    }

    private void filterData(String searchName) {
        ObservableList<Entrepans> filterData = FXCollections.observableArrayList();
        cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
        ObservableList<Entrepans> list = query.getEntrepansList();
        for (Entrepans entrepan : list) {
            if (entrepan.getName().toLowerCase().contains(searchName.toLowerCase())
                    || Double.toString(entrepan.getPrice()).toLowerCase().contains(searchName.toLowerCase())
                    || entrepan.getFilling().toLowerCase().contains(searchName.toLowerCase())) {
                filterData.add(entrepan);
            }
        }

        tableViewEntrepans.setItems(filterData);

    }  
    
}
