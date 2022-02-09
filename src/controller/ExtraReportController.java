package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/** This class controls the extra report of your choice.
 *
 */
public class ExtraReportController implements Initializable {
    public TextField extraReportTextField;
    public Button exit;
    public Button runExtraReport;

    /** This contains items initialized when window is created.
     *
     * @param url Not necessary to specify.
     * @param resourceBundle Not necessary to specify.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onExtraReportTextField(ActionEvent actionEvent) {
    }

    /** This takes the user back to the 'reports' menu.
     *
     * @param actionEvent No need to specify.
     * @throws IOException If screen is not present.
     */
    public void onExit(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ReportsMenu.fxml"));
        Stage stage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Reports Menu");
        stage.setScene(scene);
        stage.show();
    }

    public void onRunExtraReport(ActionEvent actionEvent) {
    }
}
