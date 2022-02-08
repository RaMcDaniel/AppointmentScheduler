package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/** This class controls 'add customer' screen.
 *
 */
public class AddCustomerController implements Initializable {

    public TextField addCustomerName;
    public TextField addAddress;
    public TextField addPhone;
    public TextField addPostalCode;
    public TextField addID;
    public ComboBox addCountry;
    public ComboBox addState;
    public Button addExit;
    public Button addSave;

    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onAddCustomerName(ActionEvent actionEvent) {
    }

    public void onAddAddress(ActionEvent actionEvent) {
    }

    public void onAddPostalCode(ActionEvent actionEvent) {
    }

    public void onAddPhone(ActionEvent actionEvent) {
    }

    public void onAddCountry(ActionEvent actionEvent) {
    }

    public void onAddState(ActionEvent actionEvent) {
    }

    public void onAddExit(ActionEvent actionEvent) {
    }

    public void onAddSave(ActionEvent actionEvent) {
    }
}
