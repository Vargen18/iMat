package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.util.List;
import java.util.ResourceBundle;

public class iMatController {

    @FXML
    private Button switchSceneButton, switchToFavorites, test;

    @FXML
    private ImageView escapehatch, categoryBoxImage;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label categoryBoxTitle;

    @FXML
    FlowPane categoryFlowPane;

    public List<Product> products;






    IMatDataHandler dataHandler = IMatDataHandler.getInstance();



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


    }

    @FXML
    private void switchToCheckout() throws  Exception{

        iMat.switchScene(switchSceneButton, "checkout.fxml");

    }
    @FXML
    private void populateCategoryBox() throws Exception {
        Product product = dataHandler.getProduct(25);


        System.out.println(product.getName());
        categoryBoxTitle.setText("Test");
        System.out.println(categoryBoxTitle);
        categoryBoxTitle.setText(product.getName());
        categoryBoxImage.setImage(dataHandler.getFXImage(product));

    }


}
