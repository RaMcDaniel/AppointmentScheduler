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
        Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Stage.setScene(new Scene(root, 600, 400));
        Stage.show();
    }

    /**
     * This the main method that opens the database and launches the first screen.
     * @param args not necessary to specify.
     */
    public static void main(String[] args){
        JDBC.openConnection();
        //The following lines are for testing.
        //Locale.setDefault(new Locale("Fr"));
        //TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
        launch(args);
        JDBC.closeConnection();
    }
}
