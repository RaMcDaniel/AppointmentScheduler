package model;

import helper.UsersQuery;
import javafx.collections.ObservableList;

import java.sql.SQLException;


public class Users {
    public static String userName;
    public static String userPassword;
    public static int userID;



    public Users() throws SQLException {
    }


    public static boolean validUserName(String userName) throws SQLException {
        String validName = UsersQuery.selectUserName(userName);
        if(validName == null){
            Alerts.userName.showAndWait();
            return false;
        }
        return true;
    }

    public static String validUserPassword(String password) throws SQLException {
        String validUserName = UsersQuery.selectUserPassword(password);
        if(validUserName == null){
            Alerts.userPassword.showAndWait();
            return null;
        }
        return validUserName;

    }

    /** This method takes a userName and converts to userID.
     *
     * @param userName username of person logged in
     * @return ID associated with that user
     * @throws SQLException if query is not found
     */
    public static int userNametoID(String userName) throws SQLException {
        userID = (int) UsersQuery.selectUserID(userName);
        return userID;
    }

}
