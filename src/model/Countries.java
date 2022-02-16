package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class pertains to countries.
 *
 */
public class Countries {
    private int countryID;
    private String country;

    /** A constructor for country objects.
     *
     * @param countryID country ID
     * @param country country name
     */
    public Countries(int countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }

    /** This method takes a list of coutry IDs and creates a readable list for use in combo boxes.
     *
     * @param allCountryIDs an observable list of country information.
     * @return a readble list of objects to put in the combo box.
     */
    public static ObservableList<String> getReadable(ObservableList<Countries> allCountryIDs) {
        ObservableList<String> readableCountries = FXCollections.observableArrayList();
        for(Countries c : allCountryIDs){
            String readableLine = (c.getCountryID() + " " + c.getCountry());
            readableCountries.add(readableLine);
        }
        return readableCountries;
    }

    public static int getCountryInt(String country) {
        country = country.replaceAll("[^0-9]+", " ");
        country = country.trim();
        int num = Integer.parseInt(country);
        return num;
    }

    /** The following 4 are setters and getters for country object parameters.
     *
     * @return the specified parameter
     */
    public int getCountryID() {return countryID;}
    public String getCountry() {return country;}
    public void setCountryID(int countryID) {this.countryID = countryID;}
    public void setCountry(String country) {this.country = country;}
}
