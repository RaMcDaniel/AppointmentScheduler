package model;

public class Countries {
    private int countryID;
    private String country;

    public Countries(int countryID, String country){
        this.countryID = countryID;
        this.country = country;
    }

    public int getCountryID() {return countryID;}
    public String getCountry() {return country;}

    public void setCountryID(int countryID) {this.countryID = countryID;}
    public void setCountry(String country) {this.country = country;}
}
