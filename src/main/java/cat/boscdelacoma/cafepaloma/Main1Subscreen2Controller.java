/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cat.boscdelacoma.cafepaloma;

import cat.boscdelacoma.cafepaloma.data.AppQuery;
import cat.boscdelacoma.cafepaloma.model.Worker;
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
public class Main1Subscreen2Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showWorker();
        btnWorkerUpdate.setDisable(true);
        btnWorkerDelete.setDisable(true);
        fieldWorkerSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            filterData(newValue);
        });
    }

    @FXML
    public TextField fieldWorkerName;

    @FXML
    public Button btnWorkerNew;

    @FXML
    public Button btnWorkerSave;

    @FXML
    public Button btnWorkerUpdate;

    @FXML
    public Button btnWorkerDelete;

    @FXML
    public TextField fieldWorkerSearch;

    @FXML
    public TableView<Worker> tableViewWorker;

    @FXML
    public TableColumn<Worker, Integer> colIdWorker;

    @FXML
    public TableColumn<Worker, String> colNameWorker;

    @FXML
    private void addWorker() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add Confirmation");
        dialog.setHeaderText("are you sure , u wanna add or save ");
        dialog.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Name:" + fieldWorkerName.getText() + "  " );
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            cat.boscdelacoma.cafepaloma.model.Worker worker = new cat.boscdelacoma.cafepaloma.model.Worker( fieldWorkerName.getText());
            cat.boscdelacoma.cafepaloma.data.AppQuery query = new AppQuery();
            query.addWorker(worker);
        }

    }

    private Worker worker;

    @FXML
    private void showWorker() {

        cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();

        ObservableList<Worker> list = query.getWorkerList();
        colIdWorker.setCellValueFactory(new PropertyValueFactory<Worker, Integer>("id"));
        colNameWorker.setCellValueFactory(new PropertyValueFactory<Worker, String>("name"));

        tableViewWorker.setItems(list);

    }

    @FXML
    public void mouseClicked(MouseEvent e) {
        try {

            Worker worker = tableViewWorker.getSelectionModel().getSelectedItem();
            worker = new Worker(worker.getId(), worker.getName());
            this.worker = worker;
            fieldWorkerName.setText(worker.getName());

            btnWorkerUpdate.setDisable(false);
            btnWorkerDelete.setDisable(false);
            btnWorkerSave.setDisable(true);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void UpdateWorker() {
        try {

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("update Confirmation");
            dialog.setHeaderText("are you sure , u wanna update ");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldWorkerName.getText() + "  " );
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
            cat.boscdelacoma.cafepaloma.model.Worker worker = new cat.boscdelacoma.cafepaloma.model.Worker(this.worker.getId(), fieldWorkerName.getText());
                query.UpdateWorker(worker);
                showWorker();
                clearField();
                btnWorkerUpdate.setDisable(true);
                btnWorkerDelete.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    private void DeleteWorker() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("delete Confirmation");
            dialog.setHeaderText("are you sure , u wanna delete ");
            dialog.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Name:" + fieldWorkerName.getText() + "  ");
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
            cat.boscdelacoma.cafepaloma.model.Worker worker = new cat.boscdelacoma.cafepaloma.model.Worker(this.worker.getId(), fieldWorkerName.getText());
                query.DeleteWorker(worker);
                showWorker();
                clearField();
                btnWorkerUpdate.setDisable(true);
                btnWorkerDelete.setDisable(true);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearField() {
        fieldWorkerName.setText("");
    }

    @FXML
    private void clickNew() {
        btnWorkerUpdate.setDisable(true);
        btnWorkerDelete.setDisable(true);
        clearField();
        btnWorkerSave.setDisable(false);
    }

    private void filterData(String searchName) {
        ObservableList<Worker> filterData = FXCollections.observableArrayList();
        cat.boscdelacoma.cafepaloma.data.AppQuery query = new cat.boscdelacoma.cafepaloma.data.AppQuery();
        ObservableList<Worker> list = query.getWorkerList();
        for (Worker worker : list) {
            if (worker.getName().toLowerCase().contains(searchName.toLowerCase())
                    || String.valueOf(worker.getId()).toLowerCase().contains(searchName.toLowerCase())) {
                filterData.add(worker);
            }
        }

        tableViewWorker.setItems(filterData);

    }

}
