package iMat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class iMatController implements Initializable {

    @FXML
    private Button switchSceneButton, favoritesButton, checkoutButton, myPageButton, addToFavorites;

    @FXML
    private ImageView escapehatch;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private FlowPane categoriesList, categoriesGrid, shoppingCartList;

    @FXML
    private TextField productBoxAmount;

    ProductCategory[] categories = ProductCategory.class.getEnumConstants();// for att hämta alla kategorier

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    List<Product> products = dataHandler.getProducts();

    @Override
    public void initialize(URL url, ResourceBundle rb) {



        if (iMat.scene.equals("categories.fxml")) {
            updateCategoryGrid();
            updateCategoryList();
            updateShoppingCartList();
        }

        // TODO
        //updatefavorites();

    }

    @FXML
    private void switchToCategories() throws Exception {

        iMat.escapehatch(escapehatch, "categories.fxml");

    }

    @FXML
    private void switchToFavorites() throws Exception {

        iMat.switchScene(favoritesButton, "favorites.fxml");
    }

    @FXML
    private void switchToAccount() throws Exception {

        iMat.switchScene(myPageButton, "myPage.fxml");

    }

    @FXML
    private void switchToCheckout() throws Exception {

        iMat.switchScene(checkoutButton, "checkout.fxml");

    }

    @FXML
    private void populateCategoryBox(Product product) {
        //categoryBoxTitle.setText(product.getName());
        //categoryBoxImage.setImage(new Image(product.getImageName()));

    }

    public Image getSquareImage(Image image) {

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if (image.getWidth() > image.getHeight()) {
            width = (int) image.getHeight();
            height = (int) image.getHeight();
            x = (int) (image.getWidth() - width) / 2;
            y = 0;
        } else if (image.getHeight() > image.getWidth()) {
            width = (int) image.getWidth();
            height = (int) image.getWidth();
            x = 0;
            y = (int) (image.getHeight() - height) / 2;
        } else {
            //Width equals Height, return original image
            return image;
        }
        return new WritableImage(image.getPixelReader(), x, y, width, height);
    }

    @FXML
    public void updateCategoryList() {

        for (ProductCategory pc : categories) {
            Product p = dataHandler.getProducts(pc).get(0);
            categoriesList.getChildren().add(new CategoryListItem(p, this));
        }
    }

    @FXML
    public void updateCategoryGrid() {

        for (ProductCategory pc : categories) {
            Product p = dataHandler.getProducts(pc).get(0);
            categoriesGrid.getChildren().add(new CategoryBoxItem(p, this));
        }

    }

    public void updateFavorites(){

        //Product product = addToFavorites.
        //dataHandler.favorites().add(product);

    }

    @FXML
    public void updateShoppingCartList(){
        for (int i = 0; i < 15; i++) {
            shoppingCartList.getChildren().add(new ShoppingCartListItem(products.get(i), this));
        }
    }


    @FXML
    public void removeProductFromShoppingCart(ShoppingCartListItem item){
        shoppingCartList.getChildren().remove(item);
    }


    public String switchName(Product product){
        String name = product.getCategory().name();

        switch (name) {
            case "POD":
                return "Pod";
            case "BREAD":
                return "Bread";
            case "BERRY":
                return "Pod";
            case "CITRUS_FRUIT":
                return "Bread";
            case "HOT_DRINKS":
                return "Pod";
            case "COLD_DRINKS":
                return "Bread";
            case "EXOTIC_FRUIT":
                return "Pod";
            case "FISH":
                return "Bread";
            case "VEGETABLE_FRUIT":
                return "Pod";
            case "CABBAGE":
                return "Bread";
            case "MEAT":
                return "Pod";
            case "DAIRIES":
                return "Bread";
            case "MELONS":
                return "Pod";
            case "FLOUR_SUGAR_SALT":
                return  "Bread";
            case "NUTS_AND_SEEDS":
                return  "Pod";
            case "PASTA":
                return  "Bread";
            case "POTATO_RICE":
                return  "Pod";
            case "ROOT_VEGETABLE":
                return  "Bread";
            case "FRUIT":
                return  "Bread";
            case "SWEET":
                return  "Pod";
            case "HERB":
                return "Bread";

        } return name;


    }



}

