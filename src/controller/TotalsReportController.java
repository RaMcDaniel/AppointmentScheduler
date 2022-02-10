package controller;

import helper.AppointmentsQuery;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import model.Countries;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static helper.AppointmentsQuery.getNumReportSelection;

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
    public static int month;
    public static String typeString;
    public static int totalAppointments;
    public ObservableList<Integer> months = FXCollections.observableArrayList();
    public ObservableList<String> types = FXCollections.observableArrayList();

    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < 13; i++){
            months.add(i);
        }
        totalsReportMonth.setItems(months);

        try {
            types = AppointmentsQuery.getAllAppointmentTypes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        totalsReportType.setItems(types);


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
        typeString = totalsReportType.getSelectionModel().getSelectedItem().toString();
    }

    public void onTotalsReportMonth(ActionEvent actionEvent) {
        String monthString = totalsReportMonth.getSelectionModel().getSelectedItem().toString();
        month = Countries.getCountryInt(monthString);
    }

    public void onRunReport(ActionEvent actionEvent) throws SQLException {

        totalAppointments = getNumReportSelection(month, typeString);

        numAppointmentsField.setText(String.valueOf(totalAppointments));
    }
}
