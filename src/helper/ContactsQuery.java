package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Contacts;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains SQL queries pertaining to contacts.
 *
 */
public class ContactsQuery {

    /** This method returns an observable list of all contacts and all their information.
     *
     * @return an observable list of contacts.
     * @throws SQLException if query not found
     */
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
