package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class FirstLevelDivisions {
    private int divisionID;
    private String division;
    private int countryID;

    public FirstLevelDivisions(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    public int getCountryID() {return countryID;}
    public String getDivision() {return division;}
    public int getDivisionID() {return divisionID;}

    public void setCountryID(int countryID) {this.countryID = countryID;}
    public void setDivision(String division) {this.division = division;}
    public void setDivisionID(int divisionID) {this.divisionID = divisionID;}


    public static ObservableList<String> getReadable(ObservableList<FirstLevelDivisions> allStates) {
        ObservableList<String> readableStates = FXCollections.observableArrayList();
        for(FirstLevelDivisions d : allStates){
            String readableLine = (d.getDivisionID() + " " + d.getDivision());
            readableStates.add(readableLine);
        }
        return readableStates;
    }

    public static int getStateInt(String state) {
        char c = state.charAt(0);
        int num = Character.getNumericValue(c);
        return num;
    }


}



