package controller;

import helper.JDBC;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Alerts;
import model.Users;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

import static model.Users.userName;
import static model.Users.userPassword;

/** This class controls the Login screen.
 *
 */
public class LoginController  implements Initializable{
    public static ResourceBundle rb = ResourceBundle.getBundle("main/French", Locale.getDefault());
    public TextField loginUsername;
    public TextField loginPassword;
    public Button login;
    public Button exit;
    public Label welcomeLabel;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label timeZoneLabel;
    FileWriter filewriter;
    {
        try {
            filewriter = new FileWriter("login_activity.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    PrintWriter log = new PrintWriter(filewriter);

    /** This contains information that will populate when window is called.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        log.println("Login attempts on" + " " + LocalDate.now() + ":");


        welcomeLabel.setText(rb.getString("title"));
        login.setText(rb.getString("login"));
        exit.setText(rb.getString("exit"));
        loginUsername.setPromptText(rb.getString("username"));
        loginPassword.setPromptText(rb.getString("password"));
        usernameLabel.setText(rb.getString("username"));
        passwordLabel.setText(rb.getString("password"));
        timeZoneLabel.setText(rb.getString("timezone") + ": " + ZoneId.systemDefault());

    }

    /** This is called when a user types in the username field.
     *
     * @param actionEvent type and hit enter
     * @throws SQLException if query not found
     */
    public void onLoginUsername(ActionEvent actionEvent) throws SQLException {
        userName = loginUsername.getText();
        boolean isValid = Users.validUserName(userName);
        if (!isValid){
            log.println("Invalid" + "username" + ": " + userName + "; entered at: "  + LocalTime.now() + " Failed login.");
            loginUsername.setText("");
        }
    }

    /**
     *
     * @param actionEvent on typing in password field.
     */
    public void onLoginPassword(ActionEvent actionEvent) {
        if (userName == null) {
            Alerts.inputError(rb.getString("username"), rb.getString("validusername")).showAndWait();
            log.println("Invalid username. No username entered at: "  + LocalTime.now() + " Failed login.");
            loginPassword.setText("");
        }
        else {
            userPassword = loginPassword.getText();
        }
    }

    public void onLogin(ActionEvent actionEvent) throws IOException, SQLException {

        if(userName ==null){
            Alerts.inputError(rb.getString("username"), rb.getString("validusername")).showAndWait();
            log.println("Invalid username. No username entered at: "  + LocalTime.now() + " Failed login.");
            return;
        }

        if (userPassword == null) {
            Alerts.inputError(rb.getString("password"), rb.getString("validpassword")).showAndWait();
            log.println("Username: " + userName + "; failed login, no password provided at " + LocalTime.now());
            loginPassword.setText("");
            return;
        }

        String validUserName = Users.validUserPassword(userPassword);
        if(validUserName == null) {
            log.println("Username: " + userName + "; failed login, wrong password provided at "  + LocalTime.now());
            loginPassword.setText("");
            return;
        }
        if(!validUserName.equals(userName)) {
            Alerts.userPassword.showAndWait();
            loginPassword.setText("");
            log.println("Username: " + userName + "; failed login, wrong password provided at"  + LocalTime.now());
            return;
        }
        else {
            log.println("Username: " + userName + "; successfully logged in at " + LocalTime.now());
            log.close();
            Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Welcome to Appointment Scheduler");
            stage.setScene(scene);
            stage.show();
        }
    }

    /** Here a lambda is used for the popup displayed upon exiting the program. The lambda bypasses the need to
     * set the button responses to a specific instance of the class.
     *
     * Overall, this method is called when the exit button is clicked, and it closes the connection and ends the program.
     *
     * @param actionEvent Not necessary to specify
     */
    public void onExit(ActionEvent actionEvent) {
        log.close();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(rb.getString("exitmessage"));
        alert.setContentText(rb.getString("thanks"));

        alert.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                JDBC.closeConnection();
                System.exit(0);
            }
            }));
    }
}
