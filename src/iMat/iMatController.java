package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.util.ResourceBundle;

public class iMatController {

    @FXML
    private Button switchSceneButton;

    @FXML
    private ImageView escapehatch;


    // IMatDataHandler dataHandler = new IMatDataHandler(); Den har private access. Tror vi måste komma åt den här

    @FXML
    private void switchToCategories() throws Exception{

        iMat.switchScene(switchSceneButton, "categories.fxml");
    }

    @FXML
    private void switchToFavorites() throws Exception{

        iMat.switchScene(switchSceneButton, "Favorites.fxml");
    }
}
