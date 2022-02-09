package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactsQuery {

    public static ObservableList<Contacts> getAllContactIDs() throws SQLException {
        String sql = "SELECT * from contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Contacts> allContacts = FXCollections.observableArrayList();
        while(rs.next()){
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            Contacts c = new Contacts(contactID, contactName);
            allContacts.add(c);
        }
        return allContacts;
    }

}
