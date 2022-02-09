package model;

import javafx.scene.control.Alert;

/** This class contains alerts that are referenced elsewhere in the program.
 * Alerts include........
 * and error messages in case of inappropriate user input.
 */
public class Alerts {

    public static Alert userName = new Alert(Alert.AlertType.ERROR, "That username is not found. Try again.");
    public static Alert userPassword = new Alert(Alert.AlertType.ERROR, "That password does not match. Try again.");
    public static Alert cancel = new Alert(Alert.AlertType.CONFIRMATION, "'Cancel' will lose all work on this page and return to main. Would you like to continue?");

    /** This is a general purpose alert creator. A popup box appears telling the user how to correct their input.
     * It can be generalized to any field in the program.
     * @param inputField The field with the inappropriate input.
     * @param howToCorrect A string describing acceptable inputs.
     * @return An alert describing to user how to fix input.
     */
    public static Alert inputError (String inputField, String howToCorrect){
        String fullWarning = String.format("Please correct %s. This field only accepts: %s", inputField, howToCorrect);
        Alert inputError = new Alert(Alert.AlertType.ERROR, fullWarning);
        return inputError;
    }

}
