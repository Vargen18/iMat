package iMat;

import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

public class iMat extends Application{

    @Override
    public void start(Stage stage) throws Exception {

        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        iMatController iMatController = new iMatController();
        System.out.println(dataHandler.isFirstRun()); //Testade en backend-funktion
        dataHandler.resetFirstRun();
        System.out.println(dataHandler.isFirstRun()); //Testade en backend-funktion

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMat/resources/iMat");

        Parent root = FXMLLoader.load(getClass().getResource("categories.fxml"), bundle);

        Scene scene = new Scene(root);

        stage.setTitle(bundle.getString("application.name"));
        stage.setScene(scene);
        stage.show();


        /*for(int i = 0; i < 2; i++){
            scene.setRoot(FXMLLoader.load(getClass().getResource("Favorites.fxml"), bundle));
            TimeUnit.SECONDS.sleep(1);
            scene.setRoot(FXMLLoader.load(getClass().getResource("categories.fxml"), bundle));
        }*/
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static void switchScene(Control object, String target) throws Exception{
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMat/resources/iMat");

        Parent root = FXMLLoader.load(iMat.class.getResource(target), bundle);

        object.getScene().setRoot(root);

    }
}

