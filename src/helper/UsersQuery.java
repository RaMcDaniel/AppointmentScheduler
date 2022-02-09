package helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UsersQuery {

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
}
