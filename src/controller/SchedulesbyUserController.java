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
public class SchedulesbyUserController implements Initializable {


    public Button userReportExit;
    public Label viewingForLabel;
    public TableView usersAppointmentsTable;
    public TableColumn usersIDCol;
    public TableColumn usersTitleCol;
    public TableColumn usersDescriptionCol;
    public TableColumn usersLocationCol;
    public TableColumn usersTypeCol;
    public TableColumn usersStartCol;
    public TableColumn userEndCol;
    public TableColumn usersCustomerCol;
    public TableColumn usersUserCol;
    public TableColumn usersContactCol;
    public ComboBox chooseUser;
    public Button runUserReport;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    public void onUsersAppointmentsTable(SortEvent<TableView> tableViewSortEvent) {
    }

    public void onChooseUser(ActionEvent actionEvent) {
    }

    public void onRunUserReport(ActionEvent actionEvent) {
    }

    /** This method returns to the main menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onUserReportExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Welcome to Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }
}
