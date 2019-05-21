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
    private Label ProductName;

    @FXML
    private Label Quantity;

    @FXML
    private Button RemoveProduct;

    @FXML
    private ImageView ProductImage;

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();


    public ShoppingCartListItem(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("shoppingCartListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //this.ProductImage.setImage(dataHandler.getFXImage(product));
        this.ProductName.setText(product.getName());
    }

    public void removeProductFromShoppingCart(){

    }

}
