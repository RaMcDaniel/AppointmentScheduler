package controller;

import helper.CountriesQuery;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Countries;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

        ObservableList<Countries> allCountryIDs = FXCollections.observableArrayList();
        try {
            allCountryIDs = CountriesQuery.getAllCountryIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> allCountryReadable = Countries.getReadable(allCountryIDs);

        addCountry.setItems(allCountryReadable);

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

    /** This method returns to the customer menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onAddExit(ActionEvent actionEvent)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }

    /** This method calls a method to save data, and then returns to the customer menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onAddSave(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }
}
