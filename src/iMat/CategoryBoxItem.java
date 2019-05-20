package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class CategoryBoxItem extends AnchorPane {

    @FXML
    private ImageView categoryImage;
    @FXML
    private Label categoryName;

    public CategoryBoxItem(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryBoxItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        String imagePath = product.getImageName();
        //this.categoryImage.setImage((new Image(product.getImageName())));
        this.categoryName.setText(product.getCategory().name());
    }
}
