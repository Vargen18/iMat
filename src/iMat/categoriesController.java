package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ResourceBundle;

public class categoriesController {



    @FXML
    private Button switchSceneButton;

    @FXML
    private void switchScene() throws Exception{

        iMat.switchScene(switchSceneButton, "Favorites.fxml");
    }


}
