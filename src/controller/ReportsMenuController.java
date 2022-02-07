package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ReportsMenuController {
    public Button reportMenuTotals;
    public Button reportMenuSchedules;
    public Button reportMenuExtra;
    public Button reportMenuExit;

    public void onReportMenuTotals(ActionEvent actionEvent) throws IOException{
    }

    public void onReportMenuSchedules(ActionEvent actionEvent) throws IOException{
    }

    public void onReportMenuExtra(ActionEvent actionEvent) throws IOException{
    }

    /** This takes the user back to the main menu. 
     *
     * @param actionEvent No need to specify.
     * @throws IOException If screen is not present.
     */
    public void onReportMenuExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Welcome to Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }
}
