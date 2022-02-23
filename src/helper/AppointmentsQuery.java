package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Appointments;
import java.sql.*;
import java.time.*;

/** This class contains SQL queries pertaining to appointments.
 *
 */
public class AppointmentsQuery {

    /** This method gets the largest ID number from the database, so the next appointment's ID can be predicted.
     *
     * @return the greatest ID number present in the database.
     * @throws SQLException if query not found
     */
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

    /** This method gets a list of all appointments and their information.
     *
     * @return an observable list of appointments.
     * @throws SQLException if query not found
     */
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

    /** This method gets a list of all appointments with a given contact ID.
     *
     * @param selectedContactID the provided contact ID
     * @return an observable list of appointments.
     * @throws SQLException if query not found
     */
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


    /** This method gets a list of appointments with a given customer ID.
     *
     * @param selectedCustomerID the given customer ID
     * @return an observable list of appointments.
     * @throws SQLException if query not found
     */
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

    /** This method gets a list of appointments belonging to a given customer on the current day.
     *
     * @param selectedCustomerID the given customer ID
     * @return an observable list of appointments
     * @throws SQLException if query not found
     */
    public static ObservableList<Appointments> getUpcomingAppointments(int selectedCustomerID) throws SQLException {

        String sql = "SELECT Appointment_ID, Start FROM appointments WHERE User_ID = ? AND date(Start) = current_date()";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, selectedCustomerID);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> upcomingAppointments = FXCollections.observableArrayList();
        while(rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            Timestamp start = rs.getTimestamp("Start");

            Appointments c = new Appointments(appointmentID, start);
            upcomingAppointments.add(c);
        }
        return upcomingAppointments;
    }


    /** This method selects all appointment start and end times on a given day.
     * This list is passed to a method that compares the start and end times with a new appointment's.
     *
     * @param customerID a given customer ID
     * @param date the date of the new appointment
     * @return an observable list of appointments
     * @throws SQLException if query not found
     */
    public static ObservableList<Appointments> potentialOverlaps(int customerID, LocalDate date) throws SQLException {
        String sql = "SELECT Appointment_ID, Start, End FROM appointments where Customer_ID = ? and DATE(Start) = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.setDate(2, Date.valueOf(date));
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> potentialOverlaps = FXCollections.observableArrayList();
        while(rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");

            Appointments c = new Appointments(appointmentID, start, end);
            potentialOverlaps.add(c);
        }
        return potentialOverlaps;
    }
    public static ObservableList<Appointments> potentialOverlapsMod(int customerID, LocalDate date, int apptID) throws SQLException {
        String sql = "SELECT Appointment_ID, Start, End FROM appointments where Customer_ID = ? and DATE(Start) = ? and Appointment_ID !=?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        ps.setDate(2, Date.valueOf(date));
        ps.setInt(3, apptID);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> potentialOverlaps = FXCollections.observableArrayList();
        while(rs.next()){
            int appointmentID = rs.getInt("Appointment_ID");
            Timestamp start = rs.getTimestamp("Start");
            Timestamp end = rs.getTimestamp("End");

            Appointments c = new Appointments(appointmentID, start, end);
            potentialOverlaps.add(c);
        }
        return potentialOverlaps;
    }

    /** This method gets all appointment types present in the system.
     *
     * @return an observable list of appointment types
     * @throws SQLException if query not found
     */
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

    /** This method gets all appointments fitting a given month and type.
     *
     * @param month selected month
     * @param typeString selected type
     * @return the number of appointments fitting the criteria
     * @throws SQLException  if query not found
     */
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

    /** This method adds appointments into the database.
     *
     * @param appointmentTitleAdd appointment title
     * @param descriptionAdd appointment description
     * @param locationAdd location
     * @param appTypeAdd type
     * @param startTimeStamp start time
     * @param endTimeStamp end time
     * @param customerIDAdd customer ID
     * @param passableUserID logged-in user's ID
     * @param contactIDAdd contact ID
     * @throws SQLException  if query not found
     */
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

    /** This method updates appointment information in the database.
     *
     * @param appointmentTitleAdd appointment title
     * @param descriptionAdd appointment description
     * @param locationAdd location
     * @param appTypeAdd type
     * @param startTimeStamp start time
     * @param endTimeStamp end time
     * @param customerIDAdd customer ID
     * @param passableUserID logged-in user's ID
     * @param contactIDAdd contact ID
     * @throws SQLException  if query not found
     */
    public static void updateAppointment(String appointmentTitleAdd, String descriptionAdd, String locationAdd, String appTypeAdd,
                                         Timestamp startTimeStamp, Timestamp endTimeStamp, int customerIDAdd, int passableUserID, int contactIDAdd, int apptID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
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
        ps.setInt(10, apptID);
        ps.executeUpdate();
    }

    /** This method deletes an appointment from the database matching a given appointment ID.
     *
     * @param deleteID appointment ID to be deleted
     * @throws SQLException if query not found
     */
    public static void deleteAppointment(int deleteID) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1,deleteID);
        ps.executeUpdate();
    }

    /** This method gets all appointments in the next week.
     *
     * @return an observable list of appointments
     * @throws SQLException  if query not found
     */
    public static ObservableList<Appointments> getWeekAppointments() throws SQLException {
        String sql = "SELECT * from appointments WHERE Date(Start) >= current_date() AND Date(Start) < (current_date() + 7)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> allWeekObjects = FXCollections.observableArrayList();
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
            allWeekObjects.add(c);
        }
        return allWeekObjects;
    }

    /** This method gets all appointments in the current month.
     *
     * @return an observable list of appointments
     * @throws SQLException if query not found
     */
    public static ObservableList<Appointments> getMonthAppointments() throws SQLException{
        String sql = "SELECT * from appointments WHERE MONTH(Start)=MONTH(now())\n" +
                "       and YEAR(Start)=YEAR(now())";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Appointments> allMonthObjects = FXCollections.observableArrayList();
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
            allMonthObjects.add(c);
        }
        return allMonthObjects;
    }

    /** This method checks to see if a new appointment's start time is during business hours in EST.
     *
     * @param startTimeStamp appointment start time
     * @return
     */
    public static boolean checkStart(Timestamp startTimeStamp) {
        LocalDateTime lDT = startTimeStamp.toLocalDateTime();
        ZonedDateTime zLDT = lDT.atZone(ZoneId.systemDefault());
        System.out.println(zLDT);
        ZonedDateTime estZoned = zLDT.withZoneSameInstant(ZoneId.of("EST", ZoneId.SHORT_IDS));
        LocalTime time = estZoned.toLocalTime();
        System.out.println(time);
        LocalTime open = LocalTime.of(8,00);

        if(time.isBefore(open)){
            return false;
        }
        return true;
    }

    /** This method checks to see if a new appointment's end time is during business hours in EST.
     *
     * @param endTimeStamp appointment end time
     * @return
     */
    public static boolean checkEnd(Timestamp endTimeStamp) {
        LocalDateTime lDT = endTimeStamp.toLocalDateTime();
        ZonedDateTime zLDT = lDT.atZone(ZoneId.systemDefault());
        System.out.println(zLDT);
        ZonedDateTime estZoned = zLDT.withZoneSameInstant(ZoneId.of("EST", ZoneId.SHORT_IDS));
        LocalTime time = estZoned.toLocalTime();
        System.out.println(time);
        LocalTime close = LocalTime.of(22,00);

        if(time.isAfter(close)){
            return false;
        }
        return true;
    }
}
