/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.boscdelacoma.cafepaloma;

import cat.boscdelacoma.cafepaloma.data.AppQuery;
import cat.boscdelacoma.cafepaloma.model.Refrescos;
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
public class Main1Subscreen1RefrescosController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showRefrescos();
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        fieldRefrescoSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            filterData(newValue);
        });
    }

    @FXML
    public TextField fieldRefrescoName;
    @FXML
    public TextField fieldRefrescoPrice;
    @FXML
    public TextField fieldRefrescoFlavor;
    @FXML
    public TextField fieldRefrescoSearch;


    @FXML
    public Button btnNew;

    @FXML
    public Button btnSave;

    @FXML
    public Button btnUpdate;

    @FXML
    public Button btnDelete;

    @FXML
    public TableView<Refrescos> tableView;

    @FXML
    public TableColumn<Refrescos, Integer> colId;

    @FXML
    public TableColumn<Refrescos, String> colName;

    @FXML
    public TableColumn<Refrescos, Double> colPrice;

    @FXML
    public TableColumn<Refrescos, String> colFlavor;

    @FXML
    private void addRefrescos() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Confirmation");
        dialog.setHeaderText("are you sure , u wanna add or save ");
        dialog.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Name:" + fieldRefrescoName.getText() + "  " + fieldRefrescoPrice.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            cat.boscdelacoma.cafepaloma.model.Refrescos refresco = new cat.boscdelacoma.cafepaloma.model.Refrescos(fieldRefrescoName.getText(), Double.parseDouble(fieldRefrescoPrice.getText()), fieldRefrescoFlavor.getText());
            cat.boscdelacoma.cafepaloma.data.AppQuery query = new AppQuery();
            query.addRefrescos(refresco);
        }

    }

    private Refrescos refresco;

    @FXML
    private void showRefrescos() {

        cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();

        ObservableList<Refrescos> list = query.getRefrescosList();
        colId.setCellValueFactory(new PropertyValueFactory<Refrescos, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Refrescos, String>("Name"));
        colPrice.setCellValueFactory(new PropertyValueFactory<Refrescos, Double>("Price"));
        colFlavor.setCellValueFactory(new PropertyValueFactory<Refrescos, String>("Flavor"));

        tableView.setItems(list);

    }

    @FXML
    public void mouseClicked(MouseEvent e) {
        try {

            Refrescos refresco = tableView.getSelectionModel().getSelectedItem();
            refresco = new Refrescos(refresco.getId(), refresco.getName(), refresco.getPrice(), refresco.getFlavor());
            this.refresco = refresco;
            fieldRefrescoName.setText(refresco.getName());
            fieldRefrescoPrice.setText(Double.toString(refresco.getPrice()));
            fieldRefrescoFlavor.setText(refresco.getFlavor());

            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
            btnSave.setDisable(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void UpdateRefrescos() {
        try {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("update Confirmation");
            dialog.setHeaderText("are you sure , u wanna update ");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldRefrescoName.getText() + "  " + fieldRefrescoPrice.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
                cat.boscdelacoma.cafepaloma.model.Refrescos refresco = new cat.boscdelacoma.cafepaloma.model.Refrescos(this.refresco.getId(), fieldRefrescoName.getText(), Double.parseDouble(fieldRefrescoPrice.getText()), fieldRefrescoFlavor.getText());
                query.UpdateRefrescos(refresco);
                showRefrescos();
                clearField();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void DeleteRefrescos() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("delete Confirmation");
            dialog.setHeaderText("are you sure , u wanna delete ");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldRefrescoName.getText() + "  " + fieldRefrescoPrice.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
                cat.boscdelacoma.cafepaloma.model.Refrescos refresco = new cat.boscdelacoma.cafepaloma.model.Refrescos(this.refresco.getId(), fieldRefrescoName.getText(), Double.parseDouble(fieldRefrescoPrice.getText()), fieldRefrescoFlavor.getText());
                query.DeleteRefrescos(refresco);
                showRefrescos();
                clearField();
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearField() {
        fieldRefrescoName.setText("");
        fieldRefrescoPrice.setText("");
        fieldRefrescoFlavor.setText("");
    }

    @FXML
    private void clickNew() {
        btnUpdate.setDisable(true);
        btnDelete.setDisable(true);
        clearField();
        btnSave.setDisable(false);
    }

    private void filterData(String searchName) {
        ObservableList<Refrescos> filterData = FXCollections.observableArrayList();
        cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
        ObservableList<Refrescos> list = query.getRefrescosList();
        for (Refrescos refresco : list) {
            if (refresco.getName().toLowerCase().contains(searchName.toLowerCase())
                    || Double.toString(refresco.getPrice()).toLowerCase().contains(searchName.toLowerCase())
                    || refresco.getFlavor().toLowerCase().contains(searchName.toLowerCase())) {
                filterData.add(refresco);
            }
        }

        tableView.setItems(filterData);

    }

}
