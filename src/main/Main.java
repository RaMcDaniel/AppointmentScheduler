package main;

import helper.JDBC;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/** This class hold the main method, opens the database connection, and launches the first screen.
 *
 * @throws java.io.IOException if fxml file is not present.
 */
public class Main extends Application {

    @Override
    public void start(Stage Stage) throws Exception {
        //addTestData();
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage.setScene(new Scene(root, 600, 400));
        Stage.show();
    }

    /** This method adds test data for debugging.
     * Do not use in production. This is only for debugging.
     */
    private void addTestData() {
        //Add script for test data here
    }

    /**
     * This the main method that opens the database and launches the first screen.
     * @param args
     */
    public static void main(String[] args){
        JDBC.openConnection();
        //Locale.setDefault(new Locale("Fr"));
        //TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        launch(args);
        JDBC.closeConnection();
    }
}
