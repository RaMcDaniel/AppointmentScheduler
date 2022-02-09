package model;

import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Customers {
    private String customerName;
    private String address;
    private String postalCode;
    private String phone;
    private int divisionID;
    private ObservableList<Appointments> associatedAppointments;


    public Customers(String customerName, String address, String postalCode, String phone, int divisionID, ObservableList associatedAppointments){
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.divisionID = divisionID;
        this.associatedAppointments = associatedAppointments;
    }

    public String getCustomerName(){ return customerName;}
    public String getAddress(){ return address;}
    public String getPostalCode(){ return postalCode;}
    public String getPhone(){ return phone;}
    public int getDivisionID(){ return divisionID;}
    public ObservableList<Appointments> getAssociatedAppointments(){ return associatedAppointments;}

    public void setCustomerName(String customerName){this.customerName=customerName;}
    public void setAddress(String address){this.address=address;}
    public void setPostalCode(String postalCode){this.postalCode=postalCode;}
    public void setPhone(String phone){this.phone=phone;}
    public void setDivisionID(int divisionID){this.divisionID=divisionID;}

    /**
     *
     * PROBABLY USELESS???
     */
    public void addAssociatedAppointment(Appointments appointment){
        if(appointment == null){
            Alerts.noneSelected.showAndWait();
            return;
        }
        associatedAppointments.add(appointment);
    }

    /**
     *
     * PROBABLY USELESS???
     */
    public boolean deleteAssociatedAppointment(Appointments selectedAppointment){
        if (selectedAppointment==null){
            Alerts.noneSelected.showAndWait();
            return false;
        }
        Optional<ButtonType> result = Alerts.remove.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            associatedAppointments.remove(selectedAppointment);
        }
        return true;
    }
}
