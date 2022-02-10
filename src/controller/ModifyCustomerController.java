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
import model.Alerts;
import model.Countries;
import model.Customers;
import model.FirstLevelDivisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.CustomerMenuController.*;
import static helper.CustomersQuery.insertCustomer;
import static helper.CustomersQuery.updateCustomer;


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

    private int customerIDMod = 0;
    private String customerNameMod = null;
    private String addressMod = null;
    private String postalCodeMod = null;
    private String phoneMod = null;
    private int divisionIDMod = 0;
    private int countryIDMod = 0;


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


        Customers modifyCustomer;
        try {
            modifyCustomer = (CustomersQuery.getCustomerByID(modifyCustomerID));
            modCustomerName.setText(modifyCustomer.getCustomerName());
            modAddress.setText(modifyCustomer.getAddress());
            modPostalCode.setText(modifyCustomer.getPostalCode());
            modPhone.setText(modifyCustomer.getPhone());
            modID.setText(Integer.toString(modifyCustomer.getCustomerID()));
            customerIDMod = modifyCustomer.getCustomerID();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (Object s : modCountry.getItems()){
            String sString = s.toString();
            sString = sString.replaceAll("[^0-9]+", " ");
            sString = sString.trim();
            int num = Integer.parseInt(sString);
            if(num == modifyCountryID){
                modCountry.setValue(s);
            }
        }

        String country = modCountry.getSelectionModel().getSelectedItem().toString();
        int countrySelection = Countries.getCountryInt(country);
        //countryID = countrySelection;
        ObservableList<FirstLevelDivisions> states = null;
        try {
            states = FirstLevelDivisionsQuery.getAllDivisions(countrySelection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> statesReadable = FirstLevelDivisions.getReadable(states);
        modState.setItems(statesReadable);


        for (Object s : modState.getItems()){
            String sString = s.toString();
            sString = sString.replaceAll("[^0-9]+", " ");
            sString = sString.trim();
            int num = Integer.parseInt(sString);
            System.out.println(modifyStateID);
            if(num == modifyStateID){
                modState.setValue(s);
            }
        }
    }

    public void onModCustomerName(ActionEvent actionEvent) {
        customerNameMod = modCustomerName.getText();
    }

    public void onModAddress(ActionEvent actionEvent) {
        addressMod = modAddress.getText();
    }

    public void onModPostalCode(ActionEvent actionEvent) {
        postalCodeMod = modPostalCode.getText();
    }

    public void onModPhone(ActionEvent actionEvent) {
        phoneMod = modPhone.getText();
    }

    public void onModCountry(ActionEvent actionEvent) throws SQLException {
        String country = modCountry.getSelectionModel().getSelectedItem().toString();
        int countrySelection = Countries.getCountryInt(country);
        countryIDMod = countrySelection;
        ObservableList<FirstLevelDivisions> states = FirstLevelDivisionsQuery.getAllDivisions(countrySelection);
        ObservableList<String> statesReadable = FirstLevelDivisions.getReadable(states);
        modState.setItems(statesReadable);
    }

    public void onModState(ActionEvent actionEvent) {
        String state = modState.getSelectionModel().getSelectedItem().toString();
        int stateSelection = Countries.getCountryInt(state);

        divisionIDMod = stateSelection;
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
    public void onModSave(ActionEvent actionEvent) throws IOException, SQLException {

        if(!(customerNameMod!=null && addressMod!=null && postalCodeMod!=null && phoneMod!=null && divisionIDMod!=0 && countryIDMod!=0)) {
            Alerts.inputError("form", "all fields must be completed. Press 'Enter' on keyboard after each to register.").showAndWait();
            return;
        }
        updateCustomer(customerNameMod, addressMod, postalCodeMod, phoneMod, divisionIDMod, customerIDMod);

        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }
}
