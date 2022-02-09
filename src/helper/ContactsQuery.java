package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsQuery {

    public static ObservableList<Integer> getAllContactIDs() throws SQLException {
        String sql = "SELECT Contact_ID from contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Integer> allContacts = FXCollections.observableArrayList();
        while(rs.next()){
            int contactID = rs.getInt("Contact_ID");
            allContacts.add(contactID);
        }
        return allContacts;
    }

}
