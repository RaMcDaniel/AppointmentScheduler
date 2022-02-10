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
import model.Alerts;
import model.Appointments;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;

/** This class controls the screen that contains appointment options.
 *
 */
public class AppointmentMenuController implements Initializable {
    public TableView allAppointmentsTable;
    public Button addAppointment;
    public Button modifyAppointment;
    public Button deleteAppointment;
    public Button allAppointmentsExit;
    public RadioButton allAppointmentViewWeekRadio;
    public ToggleGroup allAppointmentWeekMonth;
    public RadioButton allAppointmentViewMonthRadio;
    public RadioButton allAppointmentViewAllRadio;
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
    public static ObservableList<Appointments> allAppointmentObjects = FXCollections.observableArrayList();
    public static int appointmentIDMod;
    public static String appointmentTitleMod;
    public static String descriptionMod;
    public static String locationMod;
    public static String appTypeMod;
    public static Timestamp startMod;
    public static Timestamp endMod;
    public static int customerIDMod;
    public static int appUserIDMod;
    public static int contactIDMod;

    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allAppointmentObjects = AppointmentsQuery.getAllAppointmentObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        allAppointmentsTable.setItems(allAppointmentObjects);
        appID.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        appTitle.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        appDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        appLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        appType.setCellValueFactory(new PropertyValueFactory<>("appType"));
        startTime.setCellValueFactory(new PropertyValueFactory<>("start"));
        endTime.setCellValueFactory(new PropertyValueFactory<>("end"));
        customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        userID.setCellValueFactory(new PropertyValueFactory<>("appUserID"));
        contactID.setCellValueFactory(new PropertyValueFactory<>("contactID"));

    }

    public void onAllAppointmentsTable(SortEvent<TableView> tableViewSortEvent) {
    }

    /** This method takes user to the add appointment screen.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onAddAppointment(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Add New Appointment");
        stage.setScene(scene);
        stage.show();

    }

    /** This method views an appointment on the view/modify screen.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onModifyAppointment(ActionEvent actionEvent) throws IOException{

        Appointments modifiedAppointment = (Appointments) allAppointmentsTable.getSelectionModel().getSelectedItem();
        if(modifiedAppointment == null){
            Alerts.noneSelected.showAndWait();
            return;
        }
        appointmentIDMod = modifiedAppointment.getAppointmentID();
        appointmentTitleMod = modifiedAppointment.getAppointmentTitle();
        descriptionMod = modifiedAppointment.getDescription();
        locationMod = modifiedAppointment.getLocation();
        appTypeMod = modifiedAppointment.getAppType();
        startMod = modifiedAppointment.getStart();
        endMod = modifiedAppointment.getEnd();
        customerIDMod = modifiedAppointment.getCustomerID();
        appUserIDMod = modifiedAppointment.getAppUserID();
        contactIDMod = modifiedAppointment.getContactID();


        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyViewAppointment.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("View/Modify Appointment");
        stage.setScene(scene);
        stage.show();
    }

    public void onDeleteAppointment(ActionEvent actionEvent) throws IOException{
    }

    /** This method returns to the main menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onAllAppointmentsExit(ActionEvent actionEvent) throws IOException{
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
    public void onAllAppointmentViewWeekRadio(ActionEvent actionEvent) {
    }

    /** This radio button toggles to the monthly schedule view.
     *
     * @param actionEvent Not necessary to specify.
     */
    public void onAllAppointmentViewMonthRadio(ActionEvent actionEvent) {
    }

    /** This radio button toggles to the all appointments view.
     *
     * @param actionEvent Not necessary to specify.
     */
    public void onAllAppointmentViewAllRadio(ActionEvent actionEvent) {
    }
}
