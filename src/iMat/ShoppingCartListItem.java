package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCart;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

public class ShoppingCartListItem extends AnchorPane {

    @FXML
    private Label productName;

    @FXML
    private Label quantity, priceLabel, totalLabel;

    @FXML
    private Button removeProduct;

    @FXML
    private ImageView productImage, addButton, minusButton;

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();
    Product product;
    iMatController controller;



    public ShoppingCartListItem(Product product, iMatController controller, double amount){
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


        setQuantity((int)amount);
        setTotal((int)amount);
    }

    public void removeProductFromShoppingCart(){
        controller.removeProductFromShoppingCart(this);
    }

    @FXML
    public void switchCategory() {
        controller.updateProductGrid(product.getCategory());
    }

    public void setQuantity(int quantity){
        this.quantity.setText(quantity + " st");
    }

    @FXML void setTotal(int amount){
        double total = product.getPrice() * amount;
        this.totalLabel.setText(total + "kr");
    }

    @FXML
    public void addAmount(){
        controller.add(product);
    }

    @FXML
    public void minusAmount(){
        controller.minus(product);
    }

}
