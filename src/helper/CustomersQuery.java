package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersQuery {
    public static ObservableList<Integer> getAllCustomerIDs() throws SQLException {
        String sql = "SELECT Customer_ID from customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Integer> allCustomers = FXCollections.observableArrayList();
        while(rs.next()){
            int customerID = rs.getInt("Customer_ID");
            allCustomers.add(customerID);
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
