package controller;

import helper.AppointmentsQuery;
import helper.ContactsQuery;
import helper.CustomersQuery;
import helper.UsersQuery;
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
import model.Contacts;
import model.Countries;
import model.Customers;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/** This class controls the 'Add Appointment' screen.
 *
 */
public class AddAppointmentController implements Initializable {
    public TextField appointmentID;
    public TextField appointmentTitle;
    public TextField appointmentDescription;
    public TextField appointmentLocation;
    public ComboBox chooseContact;
    public TextField appointmentType;
    public DatePicker appointmentDate;
    public ComboBox chooseCustomerID;
    public TextField userID;
    public Button exitNewAppointment;
    public Button saveNewAppointment;
    public TextField addStartTime;
    public TextField addEndTime;

    public static int APPOINTMENT_COUNTER;


    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Users.passableUserID = Users.userNametoID(Users.userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        userID.setText(String.valueOf(Users.passableUserID));


        ObservableList<Contacts> allContactIDs = FXCollections.observableArrayList();
        try {
            allContactIDs = ContactsQuery.getAllContactIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> allContactsReadable = Contacts.getReadable(allContactIDs);
        chooseContact.setItems(allContactsReadable);

        ObservableList<Customers> allCustomerIDs = FXCollections.observableArrayList();
        try {
            allCustomerIDs = CustomersQuery.getAllCustomerIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> allCustomersReadable = Customers.getReadable(allCustomerIDs);
        chooseCustomerID.setItems(allCustomersReadable);

        int numAppointments = 0;
        try {
            numAppointments = AppointmentsQuery.getNumAppointments();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        APPOINTMENT_COUNTER = numAppointments + 1;
        appointmentID.setText(String.valueOf(APPOINTMENT_COUNTER));

    }

    public void onAppointmentID(ActionEvent actionEvent) {
    }

    public void onAppointmentTitle(ActionEvent actionEvent) {
    }

    public void onAppointmentDescription(ActionEvent actionEvent) {
    }

    public void onAppointmentLocation(ActionEvent actionEvent) {
    }

    public void onChooseContact(ActionEvent actionEvent) {
    }

    public void onAppointmentType(ActionEvent actionEvent) {
    }

    public void inAppointmentDate(ActionEvent actionEvent) {
    }

    public void onChooseCustomerID(ActionEvent actionEvent) {
    }

    public void onAddStartTime(ActionEvent actionEvent) {
    }

    public void onAddEndTime(ActionEvent actionEvent) {
    }

    /** This method returns user to the appointment menu.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onExitNewAppointment(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }

    /** This method saves the new appointment and returns user to the appointment menu.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onSaveNewAppointment(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }


}
