package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;

import java.util.ResourceBundle;

public class FavoritesController {

    @FXML
    private Button switchSceneButton;

    @FXML
    private void switchScene() throws Exception{

        ResourceBundle bundle = java.util.ResourceBundle.getBundle("iMat/resources/iMat");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("iMat.fxml"), bundle);

        switchSceneButton.getScene().setRoot(loader.getRoot());
    }
}
