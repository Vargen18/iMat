package iMat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import java.util.*;
import java.util.List;

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

    @FXML
    private Label mainLabel;


    @FXML
    ScrollPane categoriesScrollPane;

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
        mainLabel.setText("Kategorier");
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
            Product p = dataHandler.getProducts(pc).get(1);
            categoriesList.getChildren().add(new CategoryListItem(p, this));
        }
    }

    @FXML
    public void updateCategoryGrid() {

        for (ProductCategory pc : categories) {
            Product p = dataHandler.getProducts(pc).get(1);
            categoriesGrid.getChildren().add(new CategoryBoxItem(p, this));
        }

    }

    public void updateFavorites(){

        //Product product = addToFavorites.
        //dataHandler.favorites().add(product);

    }

    @FXML
    public void updateProductGrid(ProductCategory category){
        categoriesScrollPane.setVvalue(0);
        categoriesGrid.getChildren().clear();
        List<Product> products = dataHandler.getProducts(category);
        mainLabel.setText(category.toString());
        for (Product product : products) {
            categoriesGrid.getChildren().add(new ProductBoxItem(product, this));
        }

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
                return "Böner & Ärtor";
            case "BREAD":
                return "Bröd";
            case "BERRY":
                return "Bär";
            case "CITRUS_FRUIT":
                return "Citrus Frukter";
            case "HOT_DRINKS":
                return "Varm Dryck";
            case "COLD_DRINKS":
                return "Kall Dryck";
            case "EXOTIC_FRUIT":
                return "Exotiska Frukter";
            case "FISH":
                return "Fisk";
            case "VEGETABLE_FRUIT":
                return "Grönsaker";
            case "CABBAGE":
                return "Kål";
            case "MEAT":
                return "Kött";
            case "DAIRIES":
                return "Mejeri";
            case "MELONS":
                return "Melon";
            case "FLOUR_SUGAR_SALT":
                return  "Salt & Socker & Mjöl";
            case "NUTS_AND_SEEDS":
                return  "Nötter";
            case "PASTA":
                return  "Pasta";
            case "POTATO_RICE":
                return  "Ris & Potatis";
            case "ROOT_VEGETABLE":
                return  "Rotfrukter";
            case "FRUIT":
                return  "Frukt";
            case "SWEET":
                return  "Sötsaker";
            case "HERB":
                return "Örter";

        } return name;


    }



}

