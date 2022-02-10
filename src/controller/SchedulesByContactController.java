package controller;

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
import javafx.stage.Stage;
import model.Contacts;

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

    public void onChooseUser(ActionEvent actionEvent) {
    }

    public void onRunUserReport(ActionEvent actionEvent) {
    }
}
