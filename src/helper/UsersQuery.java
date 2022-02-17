package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains SQL queries pertaining to users.
 *
 */
public abstract class UsersQuery {

    /** This selects a username matching a given username to see if it exists.
     *
     * @param userName username
     * @return username, or null if the username does not exist in the database
     * @throws SQLException if query not found
     */
    public static String selectUserName(String userName) throws SQLException {
        String sql = "SELECT * FROM users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("User_Name");   //can put column index int or table column label
            return name;
        }
        return null;
    }

    /** This method checks to see if the password given matches the username given on the login screen.
     *
     * @param password user provided password
     * @return a username
     * @throws SQLException if query not found
     */
    public static String selectUserPassword(String password) throws SQLException{
        String sql = "SELECT User_Name FROM users WHERE Password = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, password);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String name = rs.getString("User_Name");   //can put column index int or table column label
            return name;
        }
        return null;
    }

    /** The method gets the userID associated with a given username.
     *
     * @param userName given username
     * @return user ID
     * @throws SQLException if query not found
     */
    public static Object selectUserID(String userName) throws SQLException {
        String sql = "SELECT User_ID from users WHERE User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int userID = rs.getInt("User_ID");
            return userID;
        }
        return null;
    }

    /** This method gets a list of all user IDs in the database.
     *
     * @return an observable list of user IDs
     * @throws SQLException if query not found
     */
    public static ObservableList<Integer> getAllUserIDs() throws SQLException {
        String sql = "SELECT User_ID from users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Integer> allIds = FXCollections.observableArrayList();
        while(rs.next()){
            int userID = rs.getInt("User_ID");
            allIds.add(userID);
        }
        return allIds;
    }
}
