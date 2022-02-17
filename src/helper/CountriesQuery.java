package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/** This class contains SQL queries pertaining to countries.
 *
 */
public class CountriesQuery {

    /** This method gets an observable list of country objects containing all country data.
     *
     * @return an observable list of countries
     * @throws SQLException if query not found
     */
    public static ObservableList<Countries> getAllCountryIDs() throws SQLException {
        String sql = "SELECT * from countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<Countries> allCountries = FXCollections.observableArrayList();
        while(rs.next()){
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Countries c = new Countries(countryID, countryName);
            allCountries.add(c);
        }
        return allCountries;
    }
}
