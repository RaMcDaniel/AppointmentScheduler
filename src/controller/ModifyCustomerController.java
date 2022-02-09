package controller;

import helper.CountriesQuery;
import helper.CustomersQuery;
import helper.FirstLevelDivisionsQuery;
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
import model.FirstLevelDivisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

        ObservableList<Countries> allCountryIDs = FXCollections.observableArrayList();
        try {
            allCountryIDs = CountriesQuery.getAllCountryIDs();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> allCountryReadable = Countries.getReadable(allCountryIDs);
        modCountry.setItems(allCountryReadable);


    }

    public void onModCustomerName(ActionEvent actionEvent) {
    }

    public void onModAddress(ActionEvent actionEvent) {
    }

    public void onModPostalCode(ActionEvent actionEvent) {
    }

    public void onModPhone(ActionEvent actionEvent) {
    }

    public void onModCountry(ActionEvent actionEvent) throws SQLException {
        String country = modCountry.getSelectionModel().getSelectedItem().toString();
        int countrySelection = Countries.getCountryInt(country);
        ObservableList<FirstLevelDivisions> states = FirstLevelDivisionsQuery.getAllDivisions(countrySelection);
        ObservableList<String> statesReadable = FirstLevelDivisions.getReadable(states);
        modState.setItems(statesReadable);
    }

    public void onModState(ActionEvent actionEvent) {
    }

    /** This method returns to the customer menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onModExit(ActionEvent actionEvent) throws IOException {
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
    public void onModSave(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }
}
