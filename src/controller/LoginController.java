package controller;

import helper.UsersQuery;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Alerts;
import model.Users;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import static model.Users.userName;
import static model.Users.userPassword;

/** This class controls the Login screen.
 *
 */
public class LoginController implements Initializable {
    public TextField loginUsername;
    public TextField loginPassword;
    public Button login;
    public Button exit;

    /** This contains information that will populate when window is called.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exit.setOnAction(e -> System.exit(0));

    }

    /** This is called when a user types in the username field.
     *
     * @param actionEvent type and hit enter
     * @throws SQLException if query not found
     */
    public void onLoginUsername(ActionEvent actionEvent) throws SQLException {
        userName = loginUsername.getText();
        boolean isValid = Users.validUserName(userName);
        if (!isValid)
            loginUsername.setText("");
    }

    /**
     *
     * @param actionEvent on typing in password field.
     */
    public void onLoginPassword(ActionEvent actionEvent) {
        if (userName == null) {
            Alerts.inputError("username", "valid usernames. Enter a username first.").showAndWait();
            loginPassword.setText("");
        }
        else {
            userPassword = loginPassword.getText();
        }
    }

    public void onLogin(ActionEvent actionEvent) throws IOException, SQLException {

        if (userPassword == null) {
            Alerts.inputError("password", "valid passwords. Enter a valid password.").showAndWait();
            loginPassword.setText("");
            return;
        }

        String validUserName = Users.validUserPassword(userPassword);
        if(!validUserName.equals(userName)) {
            Alerts.userPassword.showAndWait();
            loginPassword.setText("");
        }
        else {
            Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
            Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            Scene scene = new Scene(root, 600, 400);
            stage.setTitle("Welcome to Appointment Scheduler");
            stage.setScene(scene);
            stage.show();
        }





    }

}
