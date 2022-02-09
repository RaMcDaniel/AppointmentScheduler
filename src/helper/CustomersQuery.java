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
            int customerID = rs.getInt("Contact_ID");
            allCustomers.add(customerID);
        }
        return allCustomers;
    }

}
