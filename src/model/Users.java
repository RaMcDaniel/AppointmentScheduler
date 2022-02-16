package model;

import helper.UsersQuery;

import java.sql.SQLException;

/** This class pertains to the users who log in to the system.
 *
 */
public class Users {
    public static String userName;
    public static String userPassword;
    public static int passableUserID;

    public Users() throws SQLException {}

    /** This method checks to see if the username entered on login exists.
     *
     * @param userName the name typed on login screen.
     * @return boolean
     * @throws SQLException if the query does not exist.
     */
    public static boolean validUserName(String userName) throws SQLException {
        String validName = UsersQuery.selectUserName(userName);
        if(validName == null){
            Alerts.userName.showAndWait();
            return false;
        }
        return true;
    }

    /** This method checks to see if the password given exists and matches the username.
     *
     * @param password the password typed on login.
     * @return returns the username for use elsewhere.
     * @throws SQLException if query is not found.
     */
    public static String validUserPassword(String password) throws SQLException {
        String validUserName = UsersQuery.selectUserPassword(password);
        if(validUserName == null){
            Alerts.userPassword.showAndWait();
            return null;
        }
        return validUserName;
    }

    /** This method converts the username given on login to a userID used in other functions.
     *
     * @param userName username of person logged in
     * @return ID associated with that user
     * @throws SQLException if query is not found
     */
    public static int userNametoID(String userName) throws SQLException {
        passableUserID = (int) UsersQuery.selectUserID(userName);
        return passableUserID;
    }
}
