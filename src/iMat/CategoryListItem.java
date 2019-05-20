package iMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

//Kontrollern till list elementen på vänstra sidan där kategorier kommer vara i en lista

public class CategoryListItem extends AnchorPane {
    private iMatController controller;
    private ProductCategory category;

    @FXML private ImageView categoryImage;
    @FXML private Label categoryName;
    public CategoryListItem(Product product, iMatController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("category_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
        this.category = product.getCategory();
        String imagePath = product.getImageName();
        //this.categoryImage.setImage((new Image(product.getImageName())));
        this.categoryName.setText(category.name());
    }
}
