package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/** This class controls 'modify customer' screen.
 *
 */
public class ModifyCustomerController implements Initializable {

    public TextField modCustomerName;
    public TextField modAddress;
    public TextField modPostalCode;
    public TextField modPhone;
    public TextField modID;
    public ComboBox modCountry;
    public ComboBox modState;
    public Button modExit;
    public Button modSave;

    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onModCustomerName(ActionEvent actionEvent) {
    }

    public void onModAddress(ActionEvent actionEvent) {
    }

    public void onModPostalCode(ActionEvent actionEvent) {
    }

    public void onModPhone(ActionEvent actionEvent) {
    }

    public void onModCountry(ActionEvent actionEvent) {
    }

    public void onModState(ActionEvent actionEvent) {
    }

    public void onModExit(ActionEvent actionEvent) {
    }

    public void onModSave(ActionEvent actionEvent) {
    }
}
