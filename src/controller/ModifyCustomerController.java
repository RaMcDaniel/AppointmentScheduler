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
import model.Customers;
import model.FirstLevelDivisions;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static controller.CustomerMenuController.modifyID;


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
    //modifyID is your customer id of selected customer from last page.

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
            modifyCustomer = (CustomersQuery.getCustomerByID(modifyID));

            modCustomerName.setText(modifyCustomer.getCustomerName());
            modAddress.setText(modifyCustomer.getAddress());
            modPostalCode.setText(modifyCustomer.getPostalCode());
            modPhone.setText(modifyCustomer.getPhone());
            modID.setText(Integer.toString(modifyCustomer.getCustomerID()));
            //modState.getSelectionModel().selectFirst();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        ///////////////
        //here goes the loop where you select the country
        for (Object s : modCountry.getItems()){
            if(Integer.parseInt(s.toString()) == modifyID){
                modCountry.setValue(s);
            }
        }




        ////////////////

        ObservableList<FirstLevelDivisions> states = null;
        try {
            states = FirstLevelDivisionsQuery.getAllDivisions(modifyID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ObservableList<String> statesReadable = FirstLevelDivisions.getReadable(states);
        modState.setItems(statesReadable);





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
