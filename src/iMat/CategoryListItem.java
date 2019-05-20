package iMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

//Kontrollern till list elementen på vänstra sidan där kategorier kommer vara i en lista

public class CategoryListItem extends AnchorPane {

    @FXML private ImageView categoryImage;
    @FXML private Label categoryName;

    public CategoryListItem(Product product){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryListItem.fxml"));
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
