package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

}
