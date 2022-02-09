package model;

import helper.UsersQuery;

import java.sql.SQLException;


public class Users {
    public static String userName;
    public static String userPassword;

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

}
