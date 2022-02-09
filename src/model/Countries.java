package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Countries {
    private int countryID;
    private String country;

    public Countries(int countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }

    public static ObservableList<String> getReadable(ObservableList<Countries> allCountryIDs) {
        ObservableList<String> readableCountries = FXCollections.observableArrayList();
        for(Countries c : allCountryIDs){
            String readableLine = (c.getCountryID() + " " + c.getCountry());
            readableCountries.add(readableLine);
        }
        return readableCountries;
    }

    public int getCountryID() {return countryID;}
    public String getCountry() {return country;}

    public void setCountryID(int countryID) {this.countryID = countryID;}
    public void setCountry(String country) {this.country = country;}
}
