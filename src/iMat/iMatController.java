package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.util.ArrayList;
import java.util.ResourceBundle;

public class iMatController {

    @FXML
    private Button switchSceneButton, switchToFavorites;

    @FXML
    private ImageView escapehatch;

    @FXML
    AnchorPane anchorPane;

    @FXML
    FlowPane categoriesPane;

    @FXML
    GridPane productsPane;

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

    @FXML
    private void newRow(int n){
        productsPane.addRow(n);

    }

    public Image getSquareImage(Image image){

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if(image.getWidth() > image.getHeight()){
            width = (int) image.getHeight();
            height = (int) image.getHeight();
            x = (int)(image.getWidth() - width)/2;
            y = 0;
        }

        else if(image.getHeight() > image.getWidth()){
            width = (int) image.getWidth();
            height = (int) image.getWidth();
            x = 0;
            y = (int) (image.getHeight() - height)/2;
        }

        else{
            //Width equals Height, return original image
            return image;
        }
        return new WritableImage(image.getPixelReader(), x, y, width, height);
    }

    @FXML
    public void updateCategoryList() {
        categoriesPane.getChildren().clear();
        ArrayList<CategoryListItem> categoryListItems = new ArrayList<CategoryListItem>();
        ProductCategory[] categories = ProductCategory.class.getEnumConstants();
        for (ProductCategory pc : categories) {
            categoryListItems.add(new CategoryListItem(pc, this));
            categoriesPane.getChildren().add(new CategoryListItem(pc, this));
        }
    }
}


