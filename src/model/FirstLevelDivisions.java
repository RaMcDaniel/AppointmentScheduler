package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/** This class handles the state/provice information for customers.
 *
 */
public class FirstLevelDivisions {
    private int divisionID;
    private String division;
    private int countryID;

    /** This is a constructor for making first level division objects.
     *
     * @param divisionID the division ID
     * @param division the division name
     * @param countryID the country ID
     */
    public FirstLevelDivisions(int divisionID, String division, int countryID) {
        this.divisionID = divisionID;
        this.division = division;
        this.countryID = countryID;
    }

    /** The following 6 are setters and getters for accessing parts of first level division objects. Most are not needed.
     *
     * @return the parameter requested
     */
    public int getCountryID() {return countryID;}
    public String getDivision() {return division;}
    public int getDivisionID() {return divisionID;}

    public void setCountryID(int countryID) {this.countryID = countryID;}
    public void setDivision(String division) {this.division = division;}
    public void setDivisionID(int divisionID) {this.divisionID = divisionID;}

    /** This method returns a list of state objects that display the state name and ID. The list of objects is used to
     * make a user-friendly and readable combo box of states to choose.
     *
     * @param allStates an observable list of all states.
     * @return an readable observable list.
     */
    public static ObservableList<String> getReadable(ObservableList<FirstLevelDivisions> allStates) {
        ObservableList<String> readableStates = FXCollections.observableArrayList();
        for(FirstLevelDivisions d : allStates){
            String readableLine = (d.getDivisionID() + " " + d.getDivision());
            readableStates.add(readableLine);
        }
        return readableStates;
    }

    /** This method takes a state name and returns the assocaited ID.
     *
     * @param state state name
     * @return state ID
     */
    public static int getStateInt(String state) {
        char c = state.charAt(0);
        int num = Character.getNumericValue(c);
        return num;
    }
}



