package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class orderBox extends AnchorPane {

    @FXML
    Text total, date;

    @FXML
    FlowPane orderFlowPane;

    private Order order;

    public orderBox(Order order, iMatController controller) {
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("orderBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);


        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        this.order = order;
        for (int i = 0; i < order.getItems().size(); i++){
            ShoppingItem shoppingItem = order.getItems().get(i);
            orderFlowPane.getChildren().add(new CheckoutProductBox(shoppingItem, controller));
        }
    }

}
