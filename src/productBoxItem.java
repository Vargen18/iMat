import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class productBoxItem extends AnchorPane {

    @FXML
    private ImageView productImage;
    @FXML
    private Label productName;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String name;
    public int antal;
    public double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public productBoxItem(Product product){
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryBoxItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }




        this.productImage.setImage(dataHandler.getFXImage(product));
        this.productName.setText(product.getCategory().name());
        this.price = product.getPrice();
    }

    public void addOne() {
        antal =+ antal;
    }

    public void removeOne() {
        antal =- antal;
    }
}