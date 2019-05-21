package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

// Klassen för lådorna i mitten

public class CategoryBoxItem extends AnchorPane {

    @FXML
    private ImageView categoryImage;
    @FXML
    private Label categoryName;

    Product product;

    public CategoryBoxItem(Product product, iMatController controller){
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryBoxItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.product = product;

        this.categoryImage.setImage(dataHandler.getFXImage(product));
        this.categoryName.setText(controller.switchName(this.product));
    }





}
