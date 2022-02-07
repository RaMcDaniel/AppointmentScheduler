package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls the fxml file containing the welcome screen, the base screen for the program.
 *
 * **** needs a popup warning user if appt. within 15 min.
 *
 */
public class WelcomeController implements Initializable {


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public Label welcomeLabel;
    public Button welcomeYourSchedule;
    public Button welcomeReports;
    public Button welcomeCustomers;
    public Button welcomeAppointments;
    public Button welcomeExit;

    public void onWelcomeYourSchedule(ActionEvent actionEvent) {
    }

    public void onWelcomeReports(ActionEvent actionEvent) {
    }

    public void onWelcomeCustomers(ActionEvent actionEvent) {
    }

    public void onWelcomeAppointments(ActionEvent actionEvent) {
    }

    public void onWelcomeExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

}
