package controller;

import helper.CustomersQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Alerts;
import model.Customers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

/** This class controls the customer menu.
 *
 */
public class CustomerMenuController implements Initializable {
    public TableView allCustomersTable;
    public Button addCustomer;
    public Button modifyCustomer;
    public Button deleteCustomer;
    public Button allCustomersExit;
    public TableColumn customerIDCol;
    public TableColumn customerNameCol;
    public TableColumn customerPhoneCol;
    public TableColumn customerAddressCol;
    public TableColumn CustomerPostalCol;
    public TableColumn customerStateCol;
    public TableColumn customerCountryCol;
    public static int modifyID;
    public static ObservableList<Customers> allCustomerObjects = FXCollections.observableArrayList();

    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            allCustomerObjects = CustomersQuery.getAllCustomerObjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        allCustomersTable.setItems(allCustomerObjects);
        customerIDCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        customerPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        CustomerPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerStateCol.setCellValueFactory(new PropertyValueFactory<>("divisionID"));
        customerCountryCol.setCellValueFactory(new PropertyValueFactory<>("countryID"));


    }

    public void onAllCustomersTable(SortEvent<TableView> tableViewSortEvent) {
    }

    /** This method takes user to the 'add customer' screen.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onAddCustomer(ActionEvent actionEvent) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AddCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Modify Customer");
        stage.setScene(scene);
        stage.show();
    }

    /** This method takes user to the 'modify customer' screen.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onModifyCustomer(ActionEvent actionEvent) throws IOException{

        Customers modifiedCustomer = (Customers)allCustomersTable.getSelectionModel().getSelectedItem();
        if(modifiedCustomer == null){
            Alerts.noneSelected.showAndWait();
            return;
        }
        modifyID = modifiedCustomer.getCustomerID();

        Parent root = FXMLLoader.load(getClass().getResource("/view/ModifyCustomer.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Modify Customer");
        stage.setScene(scene);
        stage.show();
    }

    /** This method calls a function to delete a customer and returns to the main menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onDeleteCustomer(ActionEvent actionEvent) throws IOException, SQLException {

        Customers deletedCustomer = (Customers)allCustomersTable.getSelectionModel().getSelectedItem();
        if(deletedCustomer == null){
            Alerts.noneSelected.showAndWait();
            return;
        }
        int deleteID = deletedCustomer.getCustomerID();

        if(CustomersQuery.determineAssociatedAppointments(deleteID)){
            Alerts.associatedAppointments.showAndWait();
            return;
        }

        Optional<ButtonType> result = Alerts.delete.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            CustomersQuery.deleteCustomer(deleteID);
            Alerts.deleteCustomerConfirmation(deletedCustomer.getCustomerID(), deletedCustomer.getCustomerName()).showAndWait();
        }



        Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Welcome to Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    /** This method returns to the main menu when button is clicked.
     *
     * @param actionEvent Not necessary to specify.
     * @throws IOException if screen isn't present.
     */
    public void onAllCustomersExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Welcome to Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }
}
