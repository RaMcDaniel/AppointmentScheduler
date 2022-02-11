package controller;

import helper.AppointmentsQuery;
import helper.ContactsQuery;
import helper.CustomersQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;


import static controller.AppointmentMenuController.*;
import static model.Appointments.convertStringAndDateTimeStamp;
import static model.Users.passableUserID;

/** This class controls the View/Modify Appointment screen.
 *
 */
public class ModifyViewAppointmentController implements Initializable {

    public Button modifyAppointmentExit;
    public Button modAppointmentSave;
    public TextField appointmentIDModField;
    public TextField appointmentTitleModField;
    public TextField appointmentDescriptionMod;
    public TextField appointmentLocationMod;
    public ComboBox chooseContactMod;
    public TextField appointmentTypeMod;
    public DatePicker appointmentDate;
    public ComboBox chooseCustomerIDMod;
    public TextField userIDMod;
    public TextField modStartTime;
    public TextField modEndTime;
    //public static LocalDate dateAddMod;
    //public static String startHHmmMod;
    //public static String endHHmmMod;



    /** This contains information that will populate when window is called.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            passableUserID = Users.userNametoID(Users.userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userIDMod.setText(String.valueOf(passableUserID));

        ObservableList<Contacts> allContactIDs = FXCollections.observableArrayList();
        try {
            allContactIDs = ContactsQuery.getAllContactIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> allContactsReadable = Contacts.getReadable(allContactIDs);
        chooseContactMod.setItems(allContactsReadable);

        ObservableList<Customers> allCustomerIDs = FXCollections.observableArrayList();
        try {
            allCustomerIDs = CustomersQuery.getAllCustomerIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> allCustomersReadable = Customers.getReadable(allCustomerIDs);
        chooseCustomerIDMod.setItems(allCustomersReadable);



        appointmentIDModField.setText(String.valueOf(appointmentIDMod));
        appointmentTitleModField.setText(appointmentTitleMod);
        appointmentDescriptionMod.setText(descriptionMod);
        appointmentLocationMod.setText(locationMod);
        appointmentTypeMod.setText(appTypeMod);
        modStartTime.setText(startHHmm);
        modEndTime.setText(endHHmm);
        chooseCustomerIDMod.setValue(customerIDMod);
        userIDMod.setText(String.valueOf(appUserIDMod));
        chooseContactMod.setValue(contactIDMod);
        appointmentDate.setValue(dateMod);

    }

    /** This method returns user to the appointment menu.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onModifyAppointmentExit(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }


    public void onModAppointmentSave(ActionEvent actionEvent) throws IOException, SQLException {

        if(!(appointmentTitleMod!=null && descriptionMod!=null && locationMod!=null && appTypeMod!=null && customerIDMod!=0 && contactIDMod!=0 && dateMod!=null)) {
            Alerts.inputError("form", "all fields must be completed. Press 'Enter' on keyboard after each to register.").showAndWait();
            return;
        }

        Timestamp startTimeStamp = convertStringAndDateTimeStamp(startHHmm, dateMod);
        Timestamp endTimeStamp = convertStringAndDateTimeStamp(endHHmm, dateMod);


        AppointmentsQuery.updateAppointment(appointmentTitleMod, descriptionMod, locationMod, appTypeMod,
                startTimeStamp, endTimeStamp, customerIDMod, Users.passableUserID, contactIDMod, appointmentIDMod);

        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onAppointmentTitleMod(ActionEvent actionEvent) {
        appointmentTitleMod = appointmentTitleModField.getText();
    }

    public void onAppointmentDescriptionMod(ActionEvent actionEvent) {
        descriptionMod = appointmentDescriptionMod.getText();
    }

    public void onAppointmentLocationMod(ActionEvent actionEvent) {
        locationMod = appointmentLocationMod.getText();
    }

    public void onChooseContactMod(ActionEvent actionEvent) {
        String contact = chooseContactMod.getSelectionModel().getSelectedItem().toString();
        int contactSelection = Countries.getCountryInt(contact);
        contactIDMod = contactSelection;
    }

    public void onAppointmentTypeMod(ActionEvent actionEvent) {
        appTypeMod = appointmentTypeMod.getTypeSelector();
    }

    public void onAppointmentDate(ActionEvent actionEvent) {
        dateMod = appointmentDate.getValue();
    }

    public void onChooseCustomerIDMod(ActionEvent actionEvent) {
        String customer = chooseCustomerIDMod.getSelectionModel().getSelectedItem().toString();
        int customerSelection = Countries.getCountryInt(customer);
        customerIDMod = customerSelection;
    }

    public void onModStartTIme(ActionEvent actionEvent) {
        startHHmm = modStartTime.getText();
    }

    public void onModEndTime(ActionEvent actionEvent) {
        endHHmm = modEndTime.getText();
    }
}