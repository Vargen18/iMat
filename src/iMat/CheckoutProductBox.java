package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.File;
import java.io.IOException;

public class CheckoutProductBox extends AnchorPane {

    @FXML
    private ImageView productImageView;

    @FXML
    private Text productNameText;

    @FXML
    private Text productPriceText;

    @FXML
    private TextField productAmountTextField;

    @FXML
    private Text sumText;

    private Product product;

    private iMatController controller;

    private IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    private ShoppingItem shoppingItem;

    private int amount;

    public CheckoutProductBox(ShoppingItem shoppingItem, iMatController controller){
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("checkoutProductBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);



        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        this.product = shoppingItem.getProduct();
        this.controller = controller;

        this.productImageView.setImage(dataHandler.getFXImage(product));

        if(shoppingItem == null) {
            for(ShoppingItem shitem : this.dataHandler.getShoppingCart().getItems()) {
                if(shitem.getProduct().getName().equals(product.getName())) {
                    shoppingItem = shitem;
                }
            }
        }

        amount = 0;
        if(shoppingItem != null){
            this.amount = (int) shoppingItem.getAmount();
        }


        this.productNameText.setText(product.getName());
        this.productAmountTextField.setText(String.valueOf(amount));
        this.sumText.setText(Integer.toString((int) (product.getPrice() * this.amount)) + " kr");
        this.productPriceText.setText(String.valueOf(product.getPrice()) + product.getUnit());

    }
    @FXML
    public void add() {
        controller.add(product);
        if(shoppingItem == null) {
            for(ShoppingItem shitem : dataHandler.getShoppingCart().getItems()) {
                if(shitem.getProduct().getName().equals(product.getName())) {
                    shoppingItem = shitem;
                }
            }
        }
        amount++;
        productAmountTextField.setText(String.valueOf(amount));
    }
    @FXML
    public void remove() { controller.minus(product);
        amount--;
        if(amount <= 0){
            amount = 0;
        }

        productAmountTextField.setText(String.valueOf(amount));
    }

    @FXML
    public void updateAmount(){
        if(shoppingItem == null) {
            shoppingItem = controller.findShoppingItem(product);
        }
        if(shoppingItem == null) {
            dataHandler.getShoppingCart().addProduct(product);
            shoppingItem = controller.findShoppingItem(product);
        }
        int amount = Integer.parseInt(productAmountTextField.getText());
        this.shoppingItem.setAmount(amount);

        controller.updateShoppingCartList();
        this.amount = amount;
        if(this.amount <= 0){
            this.amount = 0;
        }
    }


}
