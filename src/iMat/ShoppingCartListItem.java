package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ShoppingCartListItem extends AnchorPane {

    @FXML
    private Label productName;

    @FXML
    private Label quantity;

    @FXML
    private Button removeProduct;

    @FXML
    private ImageView productImage;

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    Product product;
    iMatController controller;

    public ShoppingCartListItem(Product product, iMatController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;
        this.controller = controller;
        this.productImage.setImage(dataHandler.getFXImage(product));
        this.productName.setText(product.getName());
        this.quantity.setText("3");
    }

    public void removeProductFromShoppingCart(){
        controller.removeProductFromShoppingCart(this);
    }

}
