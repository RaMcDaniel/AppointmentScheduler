package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.FirstLevelDivisions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains SQL queries relevant to first level divisions.
 *
 */
public class FirstLevelDivisionsQuery {

    /** This methods gets all divisions that match the specified country.
     *
     * @param countryID country ID chosen by user
     * @return list of divisions matching chosen country
     * @throws SQLException if query is not found
     */
    public static ObservableList<FirstLevelDivisions> getAllDivisions(int countryID) throws SQLException {
        String sql = "SELECT * from first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        ObservableList<FirstLevelDivisions> allDivisions = FXCollections.observableArrayList();
        while(rs.next()){
            int cID = rs.getInt("Country_ID");
            String division = rs.getString("Division");
            int divisionID = rs.getInt("Division_ID");
            FirstLevelDivisions d = new FirstLevelDivisions(divisionID, division, cID);
            allDivisions.add(d);
        }
        return allDivisions;
    }

}
