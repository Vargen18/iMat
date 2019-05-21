package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class productBoxItem extends AnchorPane {

    @FXML
    private ImageView productImage;
    @FXML
    private Label productName;

    Product product;

    ShoppingItem shoppingItem;



    public int antal;
    public double total;

    public double getTotal() {
        return total;
    }


    public double getPrice() {
        return product.getPrice();
    }





    public productBoxItem(Product pro){
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = pro;


        this.productImage.setImage(dataHandler.getFXImage(product));
        this.productName.setText(product.getCategory().name());

    }

    public void addOne() {
        this.antal =+ this.antal;
    }

    public void removeOne() {
        this.antal =- this.antal;
    }

    public void totalCost(){
        this.total = this.antal * this.product.getPrice();

    }
}