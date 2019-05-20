package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;

import java.util.ResourceBundle;

public class iMatController {

    @FXML
    private Button switchSceneButton, switchToFavorites;

    @FXML
    private ImageView escapehatch;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label categoryBoxTitle;


    // IMatDataHandler dataHandler = new IMatDataHandler(); Den har private access. Tror vi måste komma åt den här

    @FXML
    private void switchToCategories() throws Exception{

        iMat.switchScene(switchSceneButton, "categories.fxml");

    }

    @FXML
    private void switchToFavorites() throws Exception{

        iMat.switchScene(switchSceneButton, "Favorites.fxml");
    }
    @FXML
    private void switchToAccount() throws  Exception{

        iMat.switchScene(switchSceneButton, "myPage.fxml");

    }

    @FXML
    private void switchToCheckout() throws  Exception{

        iMat.switchScene(switchSceneButton, "checkout.fxml");

    }
}
