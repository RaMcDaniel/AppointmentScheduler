package controller;

import helper.AppointmentsQuery;
import helper.CustomersQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Appointments;
import model.Countries;
import model.Customers;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
    public static ObservableList<Appointments> appointmentsBySelectedCustomer = FXCollections.observableArrayList();
    public static int selectedCustomerID;

    /** This method contains everything that is set when the window is initialized.
     *
     * @param url not necessary to specify
     * @param resourceBundle not necessary to specify
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customers> allCustomerIDs = FXCollections.observableArrayList();
        try {
            allCustomerIDs = CustomersQuery.getAllCustomerIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> allCustomersReadable = Customers.getReadable(allCustomerIDs);
        chooseCustomer.setItems(allCustomersReadable);
    }

    public void onCustomersAppointmentsTable(SortEvent<TableView> tableViewSortEvent) {
    }

    /** This method is called when the customer combo box is selected from.
     *
     * @param actionEvent not necessary to specify
     */
    public void onChooseCustomer(ActionEvent actionEvent) {
        String selectedCustomer = chooseCustomer.getSelectionModel().getSelectedItem().toString();
        selectedCustomerID = Countries.getCountryInt(selectedCustomer);
    }

    /** This method populates the tables with appointments fitting the selected criteria.
     *
     * @param actionEvent not necessary to specify
     */
    public void onRunCustomerReport(ActionEvent actionEvent) {
        try {
            appointmentsBySelectedCustomer = AppointmentsQuery.getAppointmentsBySelectedCustomer(selectedCustomerID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        customersAppointmentsTable.setItems(appointmentsBySelectedCustomer);
        customersIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        customersTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        customersDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        customersLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        customersTypeCol.setCellValueFactory(new PropertyValueFactory<>("appType"));
        customersStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        customersEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        customersCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customersUserCol.setCellValueFactory(new PropertyValueFactory<>("appUserID"));
        customersContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
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
