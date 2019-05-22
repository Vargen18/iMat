package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class ProductBoxItem extends AnchorPane {

    @FXML
    private ImageView productImage;
    @FXML
    private Label productTitle;
    @FXML
    private TextField amount;


    Product product;

    ShoppingItem shoppingItem;

    IMatDataHandler datahandler = IMatDataHandler.getInstance();

    iMatController controller;




    public double total;

    public double getTotal() {
        return total;
    }


    public double getPrice() {
        return product.getPrice();
    }





    public ProductBoxItem(Product product, iMatController controller){
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productBox.fxml"));
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

        this.productTitle.setText(product.getName());

    }
    @FXML
    public void add() {
        controller.add(product);
    }
    @FXML
    public void remove() { controller.minus(product);}

    public void updateamount(int amount){
        this.shoppingItem.setAmount(amount);
    }


}