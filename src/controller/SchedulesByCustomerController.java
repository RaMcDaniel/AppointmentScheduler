package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls the 'Schedules by user' screen.
 *
 */
public class SchedulesByCustomerController implements Initializable {


    public Button customerReportExit;
    public TableView customersAppointmentsTable;
    public TableColumn customersIDCol;
    public TableColumn customersTitleCol;
    public TableColumn customersDescriptionCol;
    public TableColumn customersLocationCol;
    public TableColumn customersTypeCol;
    public TableColumn customersStartCol;
    public TableColumn customersEndCol;
    public TableColumn customersCustomerCol;
    public TableColumn customersUserCol;
    public TableColumn customersContactCol;
    public ComboBox chooseCustomer;
    public Button runCustomerReport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onCustomersAppointmentsTable(SortEvent<TableView> tableViewSortEvent) {
    }

    public void onChooseCustomer(ActionEvent actionEvent) {
    }

    public void onRunCustomerReport(ActionEvent actionEvent) {
    }

    /** This method returns to the main menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onCustomerReportExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Welcome to Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }
}
