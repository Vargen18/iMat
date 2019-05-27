package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.File;
import java.io.IOException;

public class payment {


    @FXML
    Label totalCost;

    @FXML
    TextField adressField, postCostField, holderNameField, yearField, cardNumberField, cvcField;

    @FXML
    ComboBox cardTypeComboBox, monthComboBox, deliveryDayComboBox, deliveryMonthComboBox;

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    iMatController controller;




    public payment(iMatController controller) {
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("payment.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        this.controller = controller;
        this.totalCost.setText(String.valueOf(dataHandler.getShoppingCart().getTotal()) + "kr");

    }
}
