package controller;

import helper.AppointmentsQuery;
import helper.ContactsQuery;
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
import model.Contacts;
import model.Countries;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** This class controls the 'schedules by contact' screen.
 *
 */
public class SchedulesByContactController implements Initializable {
    public Button contactReportExit;
    public Label viewingForLabel;
    public TableView contactsAppointmentsTable;
    public TableColumn contactIDCol;
    public TableColumn contactTitleCol;
    public TableColumn contactDescriptionCol;
    public TableColumn contactLocationCol;
    public TableColumn contactTypeCol;
    public TableColumn contactStartCol;
    public TableColumn contactEndCol;
    public TableColumn contactCustomerCol;
    public TableColumn contactUserCol;
    public TableColumn contactContactCol;
    public ComboBox chooseUser;
    public Button runUserReport;
    public static ObservableList<Appointments> appointmentsBySelectedContact = FXCollections.observableArrayList();
    public static int selectedContactID;

    /** This method contains everything set when the window is initialized.
     *
     * @param url not necessary to specify
     * @param resourceBundle not necessary to specify
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contacts> allContactIDs = FXCollections.observableArrayList();
        try {
            allContactIDs = ContactsQuery.getAllContactIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> allContactsReadable = Contacts.getReadable(allContactIDs);
        chooseUser.setItems(allContactsReadable);
    }

    /** This method returns to the main menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onContactReportExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Welcome to Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    public void onContactsAppointmentsTable(SortEvent<TableView> tableViewSortEvent) {
    }

    /** This method controls the user combo box.
     *
     * @param actionEvent not necessary to specify
     */
    public void onChooseUser(ActionEvent actionEvent) {
        String selectedContact = chooseUser.getSelectionModel().getSelectedItem().toString();
        selectedContactID = Countries.getCountryInt(selectedContact);
    }

    /** This method populates the table with appointments matching the selected criteria.
     *
     * @param actionEvent not necessary to specify
     */
    public void onRunUserReport(ActionEvent actionEvent) {
        try {
            appointmentsBySelectedContact = AppointmentsQuery.getAppointmentsBySelectedContact(selectedContactID);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        contactsAppointmentsTable.setItems(appointmentsBySelectedContact);
        contactIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        contactTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        contactDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        contactLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        contactTypeCol.setCellValueFactory(new PropertyValueFactory<>("appType"));
        contactStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        contactEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        contactCustomerCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        contactUserCol.setCellValueFactory(new PropertyValueFactory<>("appUserID"));
        contactContactCol.setCellValueFactory(new PropertyValueFactory<>("contactID"));
    }
}
