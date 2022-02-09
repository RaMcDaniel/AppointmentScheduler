package controller;

import helper.ContactsQuery;
import helper.CustomersQuery;
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
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


import static model.Users.passableUserID;

/** This class controls the View/Modify Appointment screen.
 *
 */
public class ModifyViewAppointmentController implements Initializable {

    public Button modifyAppointmentExit;
    public Button modifyAppointmentViewSchedule;
    public Button modAppointmentSave;
    public TextField appointmentIDMod;
    public TextField appointmentTitleMod;
    public TextField appointmentDescriptionMod;
    public TextField appointmentLocationMod;
    public ComboBox chooseContactMod;
    public TextField appointmentTypeMod;
    public DatePicker appointmentDate;
    public ComboBox startTime;
    public ComboBox chooseCustomerIDMod;
    public ComboBox chooseEndTime;
    public TextField userIDMod;


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

        ObservableList<Integer> allContactIDs = null;
        try {
            allContactIDs = ContactsQuery.getAllContactIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chooseContactMod.setItems(allContactIDs);

        ObservableList<Integer> allCustomerIDs = null;
        try {
            allCustomerIDs = CustomersQuery.getAllCustomerIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        chooseCustomerIDMod.setItems(allContactIDs);

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


    /** This method returns user to their schedule when clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onModifyAppointmentViewSchedule(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/YourSchedule.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Your Schedule");
        stage.setScene(scene);
        stage.show();
    }

    public void onModAppointmentSave(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onAppointmentIDMod(ActionEvent actionEvent) {
    }

    public void onAppointmentTitleMod(ActionEvent actionEvent) {
    }

    public void onAppointmentDescriptionMod(ActionEvent actionEvent) {
    }

    public void onAppointmentLocationMod(ActionEvent actionEvent) {
    }

    public void onChooseContactMod(ActionEvent actionEvent) {
    }

    public void onAppointmentTypeMod(ActionEvent actionEvent) {
    }

    public void inAppointmentDate(ActionEvent actionEvent) {
    }

    public void onStartTime(ActionEvent actionEvent) {
    }

    public void onChooseCustomerIDMod(ActionEvent actionEvent) {
    }

    public void onChooseEndTime(ActionEvent actionEvent) {
    }
}
