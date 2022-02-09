package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Countries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountriesQuery {

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
