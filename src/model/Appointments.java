package model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Appointments {
    private int appointmentID;
    private String appointmentTitle;
    private String description;
    private String location;
    private String type;
    private Timestamp start;
    private Timestamp end;
    private int customerID;
    private int userID;
    private int contactID;


    public Appointments(int appointmentID, String appointmentTitle, String description, String location, String type,
                        Timestamp start, Timestamp end, int customerID, int userID, int contactID) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.userID = userID;
        this.contactID = contactID;
    }

    public int getAppointmentID(){ return appointmentID;}
    public String getAppointmentTitle(){return appointmentTitle;}
    public String getDescription() {return description;}
    public String getLocation(){return location;}
    public String getType() {return type;}
    public Timestamp getStart() {return start;}
    public Timestamp getEnd() {return end;}
    public int getCustomerID() {return customerID;}
    public int userID() {return userID;}
    public int getContactID() {return contactID;}
}
