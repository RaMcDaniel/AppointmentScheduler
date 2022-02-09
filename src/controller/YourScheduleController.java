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

/** This class controls the 'Your Schedule' screen.
 *
 */
public class YourScheduleController implements Initializable {
    public TableView yourScheduleTable;
    public Button yourScheduleViewAppt;
    public Button yourScheduleReturn;
    public Label viewingForLabel;
    public RadioButton thisWeekRadio;
    public ToggleGroup weekOrMonth;
    public RadioButton thisMonthRadio;
    public RadioButton viewAllRadio;
    public TableView allAppointmentsTable;
    public TableColumn appID;
    public TableColumn appTitle;
    public TableColumn appDescription;
    public TableColumn appLocation;
    public TableColumn appType;
    public TableColumn startTime;
    public TableColumn endTime;
    public TableColumn customerID;
    public TableColumn userID;
    public TableColumn contactID;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /** This method views an appointment on the view/modify screen.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onYourScheduleViewAppt(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyViewAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("View/Modify Appointment");
        stage.setScene(scene);
        stage.show();

    }

    /** This method returns to the main menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onYourScheduleReturn(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Welcome to Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    /** This radio button toggles to the weekly schedule view.
     *
     * @param actionEvent Not necessary to specify.
     */
    public void onThisWeekRadio(ActionEvent actionEvent) {
    }

    /** This radio button toggles to the monthly schedule view.
     *
     * @param actionEvent Not necessary to specify.
     */
    public void onThisMonthRadio(ActionEvent actionEvent) {
    }

    /** This radio button toggles to the all appointments view.
     *
     * @param actionEvent Not necessary to specify.
     */
    public void onViewAllRadio(ActionEvent actionEvent) {
    }

    public void onAllAppointmentsTable(SortEvent<TableView> tableViewSortEvent) {
    }
}
