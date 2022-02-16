package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Appointments;
import model.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static model.Appointments.upcomingAppointments;
import static helper.AppointmentsQuery.getUpcomingAppointments;
import static model.Users.userName;

/** This class controls the fxml file containing the welcome screen, the base screen for the program.
 *
 * **** needs a popup warning user if appt. within 15 min.
 *
 */
public class WelcomeController implements Initializable {

    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public Label welcomeLabel;
    public Button schedulesByUser;
    public Button welcomeCustomers;
    public Button welcomeAppointments;
    public Button welcomeExit;
    public Button schedulesByContact;
    public Button totalsReport;

    public ObservableList<Appointments> upcoming = FXCollections.observableArrayList();
    int currentUserID;

    {
        try {
            System.out.println(userName);
            currentUserID = Users.userNametoID(userName);
            System.out.println(currentUserID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            upcoming = getUpcomingAppointments(currentUserID);
            upcomingAppointments(upcoming);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /** This method takes the user to the 'all customers' menu.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen is not present.
     */
    public void onWelcomeCustomers(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("All Customers");
        stage.setScene(scene);
        stage.show();
    }

    /** This takes the user to the appointment menu screen with the appointment button in clicked.
     *
     * @param actionEvent No need to specify.
     * @throws IOException If screen is not present.
     */
    public void onWelcomeAppointments(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AppointmentMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Appointments Menu");
        stage.setScene(scene);
        stage.show();

    }

    /** This returns to the login screen when exit button in clicked.
     *
     * @param actionEvent No need to specify.
     * @throws IOException If screen is not present.
     */
    public void onWelcomeExit(ActionEvent actionEvent) throws IOException {
        userName = null;
        Users.userPassword = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.show();
    }

    /** This method shows the screen with the users' schedules.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen is not present.
     */
    public void onSchedulesByUser(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulesByCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Schedules by Customer");
        stage.setScene(scene);
        stage.show();
    }

    public void onSchedulesByContact(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/SchedulesByContact.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Schedules by Contact");
        stage.setScene(scene);
        stage.show();
    }

    public void onTotalsReport(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/TotalsReport.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Reports by Totals");
        stage.setScene(scene);
        stage.show();
    }
}
