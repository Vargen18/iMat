package iMat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.net.URL;
import java.util.ResourceBundle;

public class iMatController implements Initializable {

    @FXML
    private Button switchSceneButton, favoritesButton, checkoutButton, myPageButton, addToFavorites;

    @FXML
    private ImageView escapehatch;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private FlowPane categoriesList, categoriesGrid;


    ProductCategory[] categories = ProductCategory.class.getEnumConstants();// for att hämta alla kategorier

    IMatDataHandler dataHandler = IMatDataHandler.getInstance(); // Den har private access. Tror vi måste komma åt den här

    @Override
    public void initialize(URL url, ResourceBundle rb) {



        if (iMat.scene.equals("categories.fxml")) {
            updateCategoryGrid();
            updateCategoryList();
        }

        // TODO
        //updateshoppingcart();
        //updatefavorites();

    }

    @FXML
    private void switchToCategories() throws Exception {

        iMat.escapehatch(escapehatch, "categories.fxml");

    }

    @FXML
    private void switchToFavorites() throws Exception {

        iMat.switchScene(favoritesButton, "Favorites.fxml");
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
            categoriesList.getChildren().add(new CategoryListItem(p));
        }
    }

    @FXML
    public void updateCategoryGrid() {

        for (ProductCategory pc : categories) {
            Product p = dataHandler.getProducts(pc).get(1);
            //System.out.println(p.getImageName());
            categoriesGrid.getChildren().add(new CategoryBoxItem(p));
        }

    }

    public void updateFavorites(){

        //Product product = addToFavorites.
        //dataHandler.favorites().add(product);

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

