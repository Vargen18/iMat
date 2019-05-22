package iMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.ProductCategory;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

//Kontrollern till list elementen på vänstra sidan där kategorier kommer vara i en lista

public class CategoryListItem extends AnchorPane {

    @FXML
    private ImageView categoryImage;

    @FXML
    private Label categoryName;

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    iMatController controller;
    Product product;

    public CategoryListItem(Product product, iMatController controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoryListItem.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        IMatDataHandler dataHandler = IMatDataHandler.getInstance();

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        this.controller = controller;
        this.product = product;
        this.categoryImage.setImage(dataHandler.getFXImage(product));
        this.categoryName.setText(controller.switchName(product));
    }


    @FXML
    public void updateProductGrid(){
        controller.updateProductGrid(product.getCategory());
    }


}
