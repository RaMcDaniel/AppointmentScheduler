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
import model.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import static helper.CustomersQuery.insertCustomer;
import static model.Appointments.convertStringAndDateTimeStamp;

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

    public static int appointmentIDAdd;
    public static String appointmentTitleAdd;
    public static String descriptionAdd;
    public static String locationAdd;
    public static String appTypeAdd;
    public static int customerIDAdd = 0;
    public static int contactIDAdd = 0;
    public static LocalDate dateAdd;
    public static String startHHmmAdd;
    public static String endHHmmAdd;


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
        appointmentIDAdd = Integer.parseInt(appointmentID.getText());
    }

    public void onAppointmentTitle(ActionEvent actionEvent) {
        appointmentTitleAdd = appointmentTitle.getText();
    }

    public void onAppointmentDescription(ActionEvent actionEvent) {
        descriptionAdd = appointmentDescription.getText();
    }

    public void onAppointmentLocation(ActionEvent actionEvent) {
        locationAdd = appointmentLocation.getText();
    }

    public void onChooseContact(ActionEvent actionEvent) {
        String contact = chooseContact.getSelectionModel().getSelectedItem().toString();
        int contactSelection = Countries.getCountryInt(contact);
        contactIDAdd = contactSelection;
    }

    public void onAppointmentType(ActionEvent actionEvent) {
        appTypeAdd = appointmentType.getText();
    }

    public void inAppointmentDate(ActionEvent actionEvent) {
        dateAdd = appointmentDate.getValue();
    }

    public void onChooseCustomerID(ActionEvent actionEvent) {
        String customer = chooseCustomerID.getSelectionModel().getSelectedItem().toString();
        int customerSelection = Countries.getCountryInt(customer);
        customerIDAdd = customerSelection;
    }

    public void onAddStartTime(ActionEvent actionEvent) {
        startHHmmAdd = addStartTime.getText();
    }

    public void onAddEndTime(ActionEvent actionEvent) {
        endHHmmAdd = addEndTime.getText();
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
    public void onSaveNewAppointment(ActionEvent actionEvent) throws IOException, SQLException {

        if(!(appointmentTitleAdd!=null && descriptionAdd!=null && locationAdd!=null && appTypeAdd!=null && customerIDAdd!=0 && contactIDAdd!=0 && dateAdd!=null)) {
            Alerts.inputError("form", "all fields must be completed. Press 'Enter' on keyboard after each to register.").showAndWait();
            return;
        }

        Timestamp startTimeStamp = convertStringAndDateTimeStamp(startHHmmAdd, dateAdd);
        Timestamp endTimeStamp = convertStringAndDateTimeStamp(endHHmmAdd, dateAdd);


        AppointmentsQuery.insertAppointment(appointmentTitleAdd, descriptionAdd, locationAdd, appTypeAdd,
                startTimeStamp, endTimeStamp, customerIDAdd, Users.passableUserID, contactIDAdd);



        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }


}
