package model;

import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;

import java.time.LocalDate;
import java.time.LocalTime;

import static controller.LoginController.rb;

/** This class contains alerts that are referenced elsewhere in the program.
 * Alerts are triggered by various kinds of invalid input.
 * and error messages in case of inappropriate user input.
 */
public class Alerts {

    public static Alert associatedAppointments = new Alert(Alert.AlertType.ERROR, rb.getString("asscappts"));
    public static Alert userName = new Alert(Alert.AlertType.ERROR, rb.getString("usernameerror"));
    public static Alert noneSelected = new Alert(Alert.AlertType.ERROR, "Nothing Selected. Please make a selection.");
    public static Alert userPassword = new Alert(Alert.AlertType.ERROR, rb.getString("badpassword"));
    public static Alert cancel = new Alert(Alert.AlertType.CONFIRMATION, "'Cancel' will lose all work on this page and return to main. Would you like to continue?");
    public static Alert remove = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you'd like to delete this appointment?");
    public static Alert delete = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you'd like to delete this selection?");
    public static Alert businessHours = new Alert(Alert.AlertType.INFORMATION, "Business Hours are between 0800 and 2200 " +
            "EST. Please schedule your appointments within those hours.");
    public static Alert impossibleTime = new Alert(Alert.AlertType.ERROR, "Please schedule the end time AFTER the start time.");
    public static Alert noUpcoming = new Alert(Alert.AlertType.INFORMATION, "You have no upcoming appointments.");


    public static Alert deleteConfirmation (int appID, String appType){
        String fullWarning = String.format("The %s appointment: %s, has been deleted.", appType, appID);
        Alert inputError = new Alert(Alert.AlertType.ERROR, fullWarning);
        return inputError;
    }


    public static Alert overlapping (int aptID, LocalDate date, LocalTime time){
        String fullWarning = String.format("The appointment with ID: %s, at %s, on %s, is within 15 minutes.", aptID, time, date);
        Alert overlapping = new Alert(Alert.AlertType.INFORMATION, fullWarning);
        return overlapping;
    }


    /** This is a general purpose alert creator. A popup box appears telling the user how to correct their input.
     * It can be generalized to any field in the program.
     * @param inputField The field with the inappropriate input.
     * @param howToCorrect A string describing acceptable inputs.
     * @return An alert describing to user how to fix input.
     */
    public static Alert inputError (String inputField, String howToCorrect){
        String fullWarning = String.format(rb.getString("pleasecorrect") + " %s. " + rb.getString("onlyaccepts") + " %s"
                , inputField, howToCorrect);
        Alert inputError = new Alert(Alert.AlertType.ERROR, fullWarning);
        return inputError;
    }

    public static Alert deleteCustomerConfirmation(int customerID, String customerName) {
        String fullWarning = String.format("The customer, %s, with the ID: %s, has been deleted from the system.", customerName, customerID);
        Alert inputError = new Alert(Alert.AlertType.ERROR, fullWarning);
        return inputError;
    }

    public static Alert deleteAppointmentConfirmation(int appointmentID, String appType) {
        String fullWarning = String.format("The appointment of type: %s, with the ID: %s, has been cancelled.", appType, appointmentID);
        Alert inputError = new Alert(Alert.AlertType.ERROR, fullWarning);
        return inputError;
    }

}
