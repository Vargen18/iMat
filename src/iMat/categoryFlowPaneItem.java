package iMat;

import javafx.scene.image.Image;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class categoryFlowPaneItem {


    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Image getCategoryBoxImage() {
        return categoryBoxImage;
    }

    public void setCategoryBoxImage(Image categoryBoxImage) {
        this.categoryBoxImage = categoryBoxImage;
    }

    String categoryTitle;
    Image categoryBoxImage;

}
