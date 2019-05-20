package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class iMatController implements Initializable {

    @FXML
    private Button switchSceneButton, switchToFavorites, test;

    @FXML
    private ImageView escapehatch, categoryBoxImage, productBoxImage;

    @FXML
    AnchorPane anchorPane;

    @FXML
    Label categoryBoxTitle, productBoxTitle;

    @FXML
    FlowPane categoryFlowPane;

    public List<ProductCategory> productCategories = Arrays.asList(ProductCategory.values());







    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        System.out.println("Testing");

    }


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

        iMat.switchScene(switchSceneButton, "productBox.fxml");


    }

    @FXML
    private void switchToCheckout() throws  Exception{

        iMat.switchScene(switchSceneButton, "checkout.fxml");

    }
    @FXML
    private void populateProductBox() throws Exception {
        Product product = dataHandler.getProduct(25);


        System.out.println(product.getName());
        productBoxTitle.setText("Test");
        System.out.println(categoryBoxTitle);
        productBoxTitle.setText(product.getName());
        productBoxImage.setImage(dataHandler.getFXImage(product));

    }

    private void populateCategoryFlowPane() {

        for (ProductCategory productCategory : productCategories) {
            categoryFlowPaneItem item = new categoryFlowPaneItem();
            String product = "shit";
            switch (productCategory.toString()) {
                case "POD":
                    product = "Pod";
                case "BREAD":
                    product = "Bread";
                case "BERRY":
                    product = "Pod";
                case "CITRUS_FRUIT":
                    product = "Bread";
                case "HOT_DRINKS":
                    product = "Pod";
                case "COLD_DRINKS":
                    product = "Bread";
                case "EXOTIC_FRUIT":
                    product = "Pod";
                case "FISH":
                    product = "Bread";
                case "VEGETABLE_FRUIT":
                    product = "Pod";
                case "CABBAGE":
                    product = "Bread";
                case "MEAT":
                    product = "Pod";
                case "DAIRIES":
                    product = "Bread";
                case "MELONS":
                    product = "Pod";
                case "FLOUR_SUGAR_SALT":
                    product = "Bread";
                case "NUTS_AND_SEEDS":
                    product = "Pod";
                case "PASTA":
                    product = "Bread";
                case "POTATO_RICE":
                    product = "Pod";
                case "ROOT_VEGETABLE":
                    product = "Bread";
                case "FRUIT":
                    product = "Bread";
                case "SWEET":
                    product = "Pod";
                case "HERB":
                    product = "Bread";

            }










                    item.setCategoryTitle(product);
                    item.setCategoryBoxImage(dataHandler.getFXImage(dataHandler.getProducts(productCategory).get(0)));


        }
    }
                    /*POD,
                    BREAD,
                    BERRY,
                    CITRUS_FRUIT,
                    HOT_DRINKS,
                    COLD_DRINKS,
                    EXOTIC_FRUIT,
                    FISH,
                    VEGETABLE_FRUIT,
                    CABBAGE,
                    MEAT,
                    DAIRIES,
                    MELONS,
                    FLOUR_SUGAR_SALT,
                    NUTS_AND_SEEDS,
                    PASTA,
                    POTATO_RICE,
                    ROOT_VEGETABLE,
                    FRUIT,
                    SWEET,
                    HERB;*/




}
