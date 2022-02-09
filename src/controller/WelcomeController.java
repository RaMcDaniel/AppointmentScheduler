package controller;

import helper.CustomersQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Customers;
import model.Users;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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
    public Button welcomeYourSchedule;
    public Button welcomeReports;
    public Button welcomeCustomers;
    public Button welcomeAppointments;
    public Button welcomeExit;

    /** This method shows the screen with the user's schedule.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen is not present.
     */
    public void onWelcomeYourSchedule(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/YourSchedule.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Your Schedule");
        stage.setScene(scene);
        stage.show();
    }

    /** This method shows the screen with the 'reports' menu.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen is not present.
     */
    public void onWelcomeReports(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/ReportsMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Run Reports");
        stage.setScene(scene);
        stage.show();

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
        Users.userName = null;
        Users.userPassword = null;
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

}
