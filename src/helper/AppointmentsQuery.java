package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import model.Countries;
import model.Customers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentsQuery {
    public static int getNumAppointments() throws SQLException {
        String sql = "SELECT max(Appointment_ID) from appointments;";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        int numAppointments = 0;
        while(rs.next()){
            numAppointments = rs.getInt(1);
        }
        return numAppointments;
    }



    public static ObservableList<Appointments> getAllAppointmentObjects() throws SQLException {
        String sql = "SELECT * from appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> allAppointmentObjects = FXCollections.observableArrayList();
        while(rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end =rs.getTimestamp("End");
            int customerID =rs.getInt("Customer_ID");
            int userID =rs.getInt("User_ID");
            int contactID =rs.getInt("Contact_ID");

            Appointments c = new Appointments(appointmentID, appointmentTitle, description, location, type,
                    start, end, customerID, userID, contactID);
            allAppointmentObjects.add(c);
        }
        return allAppointmentObjects;
    }

    public static ObservableList<Appointments> getAppointmentsBySelectedContact(int selectedContactID) throws SQLException {

        String sql = "SELECT * from appointments WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, selectedContactID);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> appointmentsBySelectedContact = FXCollections.observableArrayList();
        while(rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end =rs.getTimestamp("End");
            int customerID =rs.getInt("Customer_ID");
            int userID =rs.getInt("User_ID");
            int contactID =rs.getInt("Contact_ID");

            Appointments c = new Appointments(appointmentID, appointmentTitle, description, location, type,
                    start, end, customerID, userID, contactID);
            appointmentsBySelectedContact.add(c);
        }
        return appointmentsBySelectedContact;
    }


    public static ObservableList<Appointments> getAppointmentsBySelectedCustomer(int selectedCustomerID) throws SQLException {

        String sql = "SELECT * from appointments WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, selectedCustomerID);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> appointmentsBySelectedContact = FXCollections.observableArrayList();
        while(rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            String appointmentTitle = rs.getString("Title");
            String description = rs.getString("Description");
            String location = rs.getString("Location");
            String type = rs.getString("Type");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end =rs.getTimestamp("End");
            int customerID =rs.getInt("Customer_ID");
            int userID =rs.getInt("User_ID");
            int contactID =rs.getInt("Contact_ID");

            Appointments c = new Appointments(appointmentID, appointmentTitle, description, location, type,
                    start, end, customerID, userID, contactID);
            appointmentsBySelectedContact.add(c);
        }
        return appointmentsBySelectedContact;

    }


    public static ObservableList<String> getAllAppointmentTypes() throws SQLException {
        String sql = "SELECT * from appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<String> allAppointmentTypes = FXCollections.observableArrayList();
        while(rs.next()){
            String type = rs.getString("Type");
            allAppointmentTypes.add(type);
        }
        return allAppointmentTypes;
    }


    public static int getNumReportSelection(int month, String typeString) throws SQLException {
        String sql = "SELECT count(*) FROM appointments WHERE month(Start) = ? AND Type = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, month);
        ps.setString(2,typeString);
        ResultSet rs = ps.executeQuery();
        int numAppointments = 0;
        while(rs.next()){
            numAppointments = rs.getInt(1);
        }
        return numAppointments;
    }


    public static void insertAppointment(String appointmentTitleAdd, String descriptionAdd, String locationAdd, String appTypeAdd,
                                         Timestamp startTimeStamp, Timestamp endTimeStamp, int customerIDAdd, int passableUserID, int contactIDAdd) throws SQLException {
        String sql = "INSERT INTO appointments(Title, Description, Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,appointmentTitleAdd);
        ps.setString(2,descriptionAdd);
        ps.setString(3,locationAdd);
        ps.setString(4,appTypeAdd);
        ps.setTimestamp(5, startTimeStamp);
        ps.setTimestamp(6, endTimeStamp);
        ps.setInt(7, customerIDAdd);
        ps.setInt(8, passableUserID);
        ps.setInt(9, contactIDAdd);
        ps.executeUpdate();
    }

    public static void updateAppointment(String appointmentTitleAdd, String descriptionAdd, String locationAdd, String appTypeAdd,
                                         Timestamp startTimeStamp, Timestamp endTimeStamp, int customerIDAdd, int passableUserID, int contactIDAdd) throws SQLException {
        String sql = "UPDATE appointments SET (Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1,appointmentTitleAdd);
        ps.setString(2,descriptionAdd);
        ps.setString(3,locationAdd);
        ps.setString(4,appTypeAdd);
        ps.setTimestamp(5, startTimeStamp);
        ps.setTimestamp(6, endTimeStamp);
        ps.setInt(7, customerIDAdd);
        ps.setInt(8, passableUserID);
        ps.setInt(9, contactIDAdd);
        ps.executeUpdate();
    }


    public static void deleteAppointment(int deleteID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,deleteID);
        ps.executeUpdate();
    }
}
