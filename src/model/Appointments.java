package model;

import javafx.collections.ObservableList;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Appointments {
    private int appointmentID;
    private String appointmentTitle;
    private String description;
    private String location;
    private String appType;
    private Timestamp start;
    private Timestamp end;
    private int customerID;
    private int appUserID;
    private int contactID;


    public Appointments(int appointmentID, String appointmentTitle, String description, String location, String appType,
                        Timestamp start, Timestamp end, int customerID, int appUserID, int contactID) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.description = description;
        this.location = location;
        this.appType = appType;
        this.start = start;
        this.end = end;
        this.customerID = customerID;
        this.appUserID = appUserID;
        this.contactID = contactID;
    }

    public Appointments(int appointmentID, Timestamp start) {
        this.appointmentID = appointmentID;
        this.start = start;
    }

    public int getAppointmentID(){ return appointmentID;}
    public String getAppointmentTitle(){return appointmentTitle;}
    public String getDescription() {return description;}
    public String getLocation(){return location;}
    public String getAppType() {return appType;}
    public Timestamp getStart() {return start;}
    public Timestamp getEnd() {return end;}
    public int getCustomerID() {return customerID;}
    public int getAppUserID() {return appUserID;}
    public int getContactID() {return contactID;}


    public static Timestamp convertStringAndDateTimeStamp(String HHmm, LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        LocalTime lt = LocalTime.parse(HHmm, dtf);
        LocalDateTime ldt = LocalDateTime.of(date, lt);
        Timestamp timestamp = Timestamp.valueOf(ldt);
        return timestamp;

    }

    public static void upcomingAppointments(ObservableList<Appointments> upcomingAppointments){
        for (Appointments a :upcomingAppointments){
            Timestamp timestamp = a.getStart();
            //LocalTime eight = LocalTime.of(8, 0);
            //LocalDateTime now = LocalDateTime.of(LocalDate.now(), eight);
            //System.out.println(now);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime ldt = timestamp.toLocalDateTime();
            long timeDifference = ChronoUnit.MINUTES.between(now, ldt);
            System.out.println(timeDifference);
            if(timeDifference <= 15){
                int aptID = a.getAppointmentID();
                LocalDate date = ldt.toLocalDate();
                LocalTime time = ldt.toLocalTime();
                Alerts.overlapping(aptID, date, time).showAndWait();
                return;
            }
            }
        Alerts.noUpcoming.showAndWait();
        }
}
