package iMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;

//Kontrollern till list elementen på vänstra sidan där kategorier kommer vara i en lista

public class CategoryListItem extends AnchorPane {
    private iMatController controller;
    private ProductCategory category;

    @FXML private ImageView categoryImage;
    @FXML private Label categoryName;
    public CategoryListItem(ProductCategory category, iMatController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("category_listitem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
        this.category = category;
        String imagePath = "resources\\images" + category.name() + ".jpg";
        this.categoryImage.setImage(controller.getSquareImage(new Image(getClass().getClassLoader().getResourceAsStream(imagePath))));
        this.categoryName.setText(category.name());
    }
}
