package model;

import helper.AppointmentsQuery;
import javafx.collections.ObservableList;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/** This class pertains to appointments.
 *
 */
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


    /** A constructor for a certain type of appointment object.
     *
     * @param appointmentID appointment ID
     * @param appointmentTitle title
     * @param description description
     * @param location location
     * @param appType type
     * @param start start time
     * @param end end time
     * @param customerID customer ID
     * @param appUserID user ID
     * @param contactID contact ID
     */
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

    /** A constructor for a certain type of appointment object.
     *
     * @param appointmentID appointment ID
     * @param start start time
     */
    public Appointments(int appointmentID, Timestamp start) {
        this.appointmentID = appointmentID;
        this.start = start;
    }

    /** A constructor for a certain type of appointment object.
     *
     * @param appointmentID appointment ID
     * @param start start time
     * @param end end time
     */
    public Appointments(int appointmentID, Timestamp start, Timestamp end) {
        this.appointmentID = appointmentID;
        this.start = start;
        this.end = end;
    }

    /** This method takes a list of appointments from the database, and an appointment the user is trying to add, and runs comparisons.
     *  It compares start and end times to determine if the user's appointment has any overlap.
     *
     * @param startTimeStamp start time
     * @param endTimeStamp end time
     * @param dateAdd date
     * @param customerIDAdd customer ID
     * @return a boolean
     * @throws SQLException if query is not found
     */
    public static boolean checkOverlaps(Timestamp startTimeStamp, Timestamp endTimeStamp, LocalDate dateAdd, int customerIDAdd) throws SQLException {
        ObservableList<Appointments> potentialOverlaps = AppointmentsQuery.potentialOverlaps(customerIDAdd, dateAdd);
        for(Appointments a : potentialOverlaps){
            if(startTimeStamp.before(a.getStart()) && endTimeStamp.after(a.getStart())){
                return true;
            }
            if(startTimeStamp.equals(a.getStart())){
                return  true;
            }
            if(startTimeStamp.after(a.getStart()) && (endTimeStamp.before(a.getEnd()) || endTimeStamp.equals(a.getEnd()))){
                return true;
            }
            if(startTimeStamp.after(a.getStart()) && startTimeStamp.before(a.getEnd())){
                return true;
            }
        }
        return false;
    }

    /** The following are setters and getters for appointment object parameters.
     *
     * @return the specified parameter
     */
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


    /** This method takes a user provided time string and converts it to a timestamp for the database.
     *
     * @param HHmm time string
     * @param date LocalDate from calendar box
     * @return a timestamp
     */
    public static Timestamp convertStringAndDateTimeStamp(String HHmm, LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HHmm");
        LocalTime lt = LocalTime.parse(HHmm, dtf);
        LocalDateTime ldt = LocalDateTime.of(date, lt);
        Timestamp timestamp = Timestamp.valueOf(ldt);
        return timestamp;
    }

    /** This method gets the time of login and compares it to all appointments that day to determine if any are within 15 min.
     *
     * @param upcomingAppointments an observable list of appointments
     */
    public static void upcomingAppointments(ObservableList<Appointments> upcomingAppointments){
        for (Appointments a :upcomingAppointments){
            Timestamp timestamp = a.getStart();
            int id = a.getAppointmentID();
            //System.out.println(id + timestamp.toString());
            LocalDateTime now = LocalDateTime.now();
            //System.out.println(now);
            LocalDateTime ldt = timestamp.toLocalDateTime();
            //System.out.println(ldt);
            long timeDifference = ChronoUnit.MINUTES.between(now, ldt);
            //System.out.println(timeDifference);
            if(timeDifference > 0 && timeDifference <= 15){
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
