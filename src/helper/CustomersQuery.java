package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains SQL queries pertaining to customers.
 *
 */
public class CustomersQuery {

    /** This method gets all customer ID and names.
     *
     * @return an observable list of customers.
     * @throws SQLException if query not found
     */
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

    /** THis creates a complete customer objects that can be passed to the modify screen.
     *
     * @return an observable list of customers
     * @throws SQLException if query not found
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

    /** This method gets the largest customer ID from the database, so the next assigned customer ID can be predicted.
     *
     * @return the highest customer ID in the database
     * @throws SQLException if query not found
     */
    public static int getNumCustomers() throws SQLException {
        String sql = "SELECT max(Customer_ID) from customers;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int numCustomers = 0;
        while(rs.next()){
            numCustomers = rs.getInt(1);
        }
        return numCustomers;
    }

    /** This method adds a new customer to the database.
     *
     * @param customerName customer's name
     * @param address address
     * @param postalCode postal code
     * @param phone phone number
     * @param divisionID state/province
     * @throws SQLException if query not found
     */
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

    /** This method updates a customer in the database.
     *
     * @param customerName customer's name
     * @param address address
     * @param postalCode postal code
     * @param phone phone number
     * @param divisionID state/province
     * @throws SQLException if query not found
     */
    public static void updateCustomer(String customerName, String address, String postalCode, String phone, int divisionID, int customerID) throws SQLException {
        String sql = "UPDATE customers SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,customerName);
        ps.setString(2,address);
        ps.setString(3,postalCode);
        ps.setString(4,phone);
        ps.setInt(5, divisionID);
        ps.setInt(6, customerID);
        ps.executeUpdate();
    }

    /** This method returns all appointments associated with a given customer, so they can be deleted if none are found.
     *
     * @param deleteID the customer ID you are trying to delete
     * @return true or false
     * @throws SQLException if query not found
     */
    public static boolean determineAssociatedAppointments(int deleteID) throws SQLException {
        String sql = "SELECT count(*) FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, deleteID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int cID = rs.getInt(1);
            if(cID > 0){
                return true;
            }
        }
        return false;
    }

    /** This method deletes a customer with a given customer ID from the database.
     *
     * @param deleteID the given customer ID
     * @throws SQLException if query not found
     */
    public static void deleteCustomer(int deleteID) throws SQLException {
        String sql = "DELETE FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,deleteID);
        ps.executeUpdate();
    }

    /** This method gets all the customer's information, including the country ID from a joined table.
     *
     * @param modifyID the customer ID
     * @return a lsit of customers
     * @throws SQLException if query not found
     */
    public static Customers getCustomerByID(int modifyID) throws SQLException {
        String sql = "SELECT * from customers, first_level_divisions WHERE customers.Division_ID = first_level_divisions.Division_ID AND Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,modifyID);
        ResultSet rs = ps.executeQuery();
        Customers c = null;
        while (rs.next()) {
            int customerID = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerPhone = rs.getString("Phone");
            String customerAddress = rs.getString("Address");
            String postalCode = rs.getString("Postal_Code");
            int state = rs.getInt("Division_ID");
            int country = rs.getInt("Country_ID");

            c = new Customers(customerID, customerName, customerAddress, postalCode, customerPhone, state, country);
        }
        return c;
    }

    public static void deleteCustomerAppointments(int deleteID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,deleteID);
        ps.executeUpdate();
    }
}
