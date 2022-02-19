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

import static helper.CustomersQuery.insertCustomer;


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
    public static int CUSTOMER_COUNTER;

    private int customerID;
    private String customerName = null;
    private String address = null;
    private String postalCode = null;
    private String phone = null;
    private int divisionID = 0;
    private int countryID = 0;

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

        int numCustomers = 0;
        try {
            numCustomers = CustomersQuery.getNumCustomers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        CUSTOMER_COUNTER = numCustomers + 1;
       // addID.setText(String.valueOf(CUSTOMER_COUNTER));

    }

    public void onAddCustomerName(ActionEvent actionEvent) {
        customerName = addCustomerName.getText();
    }

    public void onAddAddress(ActionEvent actionEvent) {
        address = addAddress.getText();
    }

    public void onAddPostalCode(ActionEvent actionEvent) {
        postalCode = addPostalCode.getText();
    }

    public void onAddPhone(ActionEvent actionEvent) {
        phone = addPhone.getText();
    }

    public void onAddCountry(ActionEvent actionEvent) throws SQLException {
        String country = addCountry.getSelectionModel().getSelectedItem().toString();
        int countrySelection = Countries.getCountryInt(country);
        countryID = countrySelection;
        ObservableList<FirstLevelDivisions> states = FirstLevelDivisionsQuery.getAllDivisions(countrySelection);
        ObservableList<String> statesReadable = FirstLevelDivisions.getReadable(states);
        addState.setItems(statesReadable);
    }

    public void onAddState(ActionEvent actionEvent) {
        String state = addState.getSelectionModel().getSelectedItem().toString();
        int stateSelection = Countries.getCountryInt(state);

        divisionID = stateSelection;

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
    public void onAddSave(ActionEvent actionEvent) throws IOException, SQLException {
        customerName = addCustomerName.getText();
        address = addAddress.getText();
        postalCode = addPostalCode.getText();
        phone = addPhone.getText();

        if(!(customerName!=null && address!=null && postalCode!=null && phone!=null && divisionID!=0 && countryID!=0)) {
            Alerts.inputError("form", "all fields must be completed.").showAndWait();
            return;
        }
        if(!(customerName!="" && address!="" && postalCode!="" && phone!="")){
            Alerts.inputError("form", "all fields must be completed.").showAndWait();
            return;
        }
        insertCustomer(customerName, address, postalCode, phone, divisionID);

        Parent root = FXMLLoader.load(getClass().getResource("/view/CustomerMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Customer Menu");
        stage.setScene(scene);
        stage.show();
    }
}
