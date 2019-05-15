package iMat;

import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class iMat extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        System.out.println(dataHandler.isFirstRun()); //Testade en backend-funktion
        dataHandler.resetFirstRun();
        System.out.println(dataHandler.isFirstRun()); //Testade en backend-funktion

        System.out.println("This is a commit test");


        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMat/resources/iMat");

        Parent root = FXMLLoader.load(getClass().getResource("iMat.fxml"), bundle);

        Parent test = FXMLLoader.load(getClass().getResource("iMat.fxml"), bundle);

        Scene scene = new Scene(root);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static  void changeScene() {





    }

}

