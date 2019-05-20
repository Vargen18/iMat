package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.util.ResourceBundle;

public class iMatController {

    @FXML
    private Button switchSceneButton, switchToFavorites;

    @FXML
    private ImageView escapehatch, categoryBoxImage;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label categoryBoxTitle;






    IMatDataHandler dataHandler = IMatDataHandler.getInstance(); // Den har private access. Tror vi måste komma åt den här



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

        iMat.switchScene(switchSceneButton, "categorybox.fxml");
        Product product = dataHandler.getProduct(25);
        populateCategoryBox(product);

    }

    @FXML
    private void switchToCheckout() throws  Exception{

        iMat.switchScene(switchSceneButton, "checkout.fxml");

    }

    private void populateCategoryBox(Product product) {
        categoryBoxTitle.setText(product.getName());
        categoryBoxImage.setImage(new Image(product.getImageName()));

    }
}
