package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls the 'Total' report.
 *
 */
public class TotalsReportController implements Initializable {
    public TextField totalReportTextField;
    public Button exit;
    public ComboBox totalsReportType;
    public ComboBox totalsReportMonth;
    public TextField numAppointmentsField;
    public Button runReport;

    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onTotalReportsTextField(ActionEvent actionEvent) {
    }

    /** This takes the user back to the main menu.
     *
     * @param actionEvent No need to specify.
     * @throws IOException If screen is not present.
     */
    public void onExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Welcome to Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    public void onTotalsReportType(ActionEvent actionEvent) {
    }

    public void onTotalsReportMonth(ActionEvent actionEvent) {
    }

    public void onRunReport(ActionEvent actionEvent) {
    }
}
