package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersQuery {
    public static ObservableList<Customers> getAllCustomerIDs() throws SQLException {
        String sql = "SELECT * from customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customers> allCustomers = FXCollections.observableArrayList();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            Customers c = new Customers(customerID, customerName);
            allCustomers.add(c);
        }
        return allCustomers;
    }

    /** THis creates a complete customer objects that can be passed to modify screen
     *
     * @return
     * @throws SQLException
     */
    public static ObservableList<Customers> getAllCustomerObjects() throws SQLException {
        String sql = "SELECT * from customers, first_level_divisions WHERE customers.Division_ID = first_level_divisions.Division_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Customers> allCustomerObjects = FXCollections.observableArrayList();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerPhone = rs.getString("Phone");
            String customerAddress = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            int state = rs.getInt("Division_ID");
            int country =rs.getInt("Country_ID");

            Customers c = new Customers(customerID, customerName, customerAddress, postalCode, customerPhone, state, country);
            allCustomerObjects.add(c);
        }
        return allCustomerObjects;
    }


    public static int getNumCustomers() throws SQLException {
        String sql = "SELECT count(*) FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int numCustomers = 0;
        while(rs.next()){
            numCustomers = rs.getInt(1);
        }
        return numCustomers;
    }

    public static void insertCustomer(String customerName, String address, String postalCode, String phone, int divisionID) throws SQLException {
        String sql = "INSERT INTO customers(Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        ps.setString(4,phone);
        ps.setInt(5, divisionID);
        ps.executeUpdate();
    }


}
