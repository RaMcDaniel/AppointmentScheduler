package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/** This class pertains to customers in the system.
 *
 */
public class Customers {
    private int customerID;
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;
    private int countryID;
    private ObservableList<Appointments> associatedAppointments;

    /** A constructor for customer objects.
     *
     * @param customerID customer ID
     * @param customerName customer name
     * @param address customer address
     * @param postalCode postal code
     * @param phone phone number
     * @param divisionID state/province ID
     * @param countryID country ID
     */
    public Customers(int customerID, String customerName, String address, String postalCode, String phone, int divisionID, int countryID){
        this.customerID = customerID;
        this.customerName = customerName;
        this.phone = phone;
        this.address = address;
        this.postalCode = postalCode;
        this.divisionID = divisionID;
        this.countryID = countryID;
    }

    /** A constructor for customer objects.
     *
     * @param customerID customer ID
     * @param customerName customer name
     */
    public Customers(int customerID, String customerName){
        this.customerName = customerName;
        this.customerID = customerID;
    }

    /** This method creates a list of country names and IDs for use in a combo box.
     *
     * @param allCustomerIDs an observable list of customers.
     * @return a readable list of countries and IDs.
     */
    public static ObservableList<String> getReadable(ObservableList<Customers> allCustomerIDs) {
        ObservableList<String> readableCountries = FXCollections.observableArrayList();
        for(Customers c : allCustomerIDs){
            String readableLine = (c.getCustomerID() + " " + c.getCustomerName());
            readableCountries.add(readableLine);
        }
        return readableCountries;
    }

    /** This method gets a customer ID from a customer name.
     *
     * @param customer customer name
     * @return customer ID
     */
    public static int getCustomerInt(String customer) {
        char c = customer.charAt(0);
        int num = Character.getNumericValue(c);
        return num;
    }

    public int getCustomerID(){return customerID;}
    public String getCustomerName(){ return customerName;}
    public String getPhone(){ return phone;}
    public String getAddress(){ return address;}
    public String getPostalCode(){ return postalCode;}
    public int getDivisionID(){ return divisionID;}
    public int getCountryID(){return countryID;}
    public ObservableList<Appointments> getAssociatedAppointments(){ return associatedAppointments;}

    public void setCustomerID(int customerID){this.customerID=customerID;}
    public void setCustomerName(String customerName){this.customerName=customerName;}
    public void setAddress(String address){this.address=address;}
    public void setPostalCode(String postalCode){this.postalCode=postalCode;}
    public void setPhone(String phone){this.phone=phone;}
    public void setDivisionID(int divisionID){this.divisionID=divisionID;}

}
