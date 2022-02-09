package helper;

import java.sql.*;

public class JDBC {
    private static final String protocol = "jdbc";
    private static final String vendor = ":mysql:";
    private static final String location = "//localhost/";
    private static final String databaseName = "client_schedule";
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String userName = "sqlUser"; // Username
    private static String password = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}

    /**
     *
     *
     *

     //insert is how to insert row into a table, check it in mysql workbench
     //returning an int number of rows
     public static int insert(String fruitName, int colorID) throws SQLException {
     //fruit_id column excluded bc it's primary ID and auto incremented, will auto add to new entries.
     // fruits is the sql table  case doesn't matter
     //fruit_name and color_id are columns in table
     // ? is a bind variable, they're indexed, ?1 and ?2
     String sql = "INSERT INTO fruits(fruit_name, color_id) VALUES(?, ?)";
     // connection is public variable in JDBC class
     PreparedStatement ps = JDBC.connection.prepareStatement(sql);
     //prepare string setter methods, 1 and 2 parameterindex are the bind variables
     ps.setString(1,fruitName);
     ps.setInt(2, colorID);
     //this actually inserts the row(s) and returns how many rows (here, it's 1)
     int rowsAffected = ps.executeUpdate();
     return rowsAffected;
     }

     //this method will update fruit name, identifying it by ID first
     public static int update(int fruitID, String fruitName) throws SQLException {
     String sql = "UPDATE fruits SET fruit_Name = ? WHERE fruit_ID = ?";
     PreparedStatement ps = JDBC.connection.prepareStatement(sql);
     ps.setString(1, fruitName);
     ps.setInt(2, fruitID);
     int rowsAffected = ps.executeUpdate();
     return rowsAffected;
     }


     public static int delete(int fruitID) throws SQLException {
     String sql = "DELETE FROM FRUITS WHERE FRUIT_ID = ?";
     PreparedStatement ps = JDBC.connection.prepareStatement(sql);
     ps.setInt(1,fruitID);
     int rowsAffected = ps.executeUpdate();
     return rowsAffected;
     }

     public static void select() throws SQLException {
     String sql = "SELECT * FROM FRUITS";
     PreparedStatement ps = JDBC.connection.prepareStatement(sql);
     ResultSet rs = ps.executeQuery();
     while(rs.next()){
     int fruitID = rs.getInt("FRUIT_ID");   //can put column index int or table column label
     String fruitName = rs.getString("FRUIT_NAME");
     System.out.print(fruitID + " | " + fruitName + "\n");
     }

     }

     public static void select(int colorID) throws SQLException {
        String sql = "SELECT * FROM FRUITS WHERE Color_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, colorID);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int fruitID = rs.getInt("FRUIT_ID");   //can put column index int or table column label
            String fruitName = rs.getString("FRUIT_NAME");
            int color_ID = rs.getInt("Color_ID");
            System.out.print(fruitID + " | " + fruitName + " | " + color_ID + "\n");
        }
    }

     */
