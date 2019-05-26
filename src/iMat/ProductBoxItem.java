package iMat;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.IMatDataHandler;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.File;
import java.io.IOException;

public class ProductBoxItem extends AnchorPane {

    @FXML
    private ImageView productImage;
    @FXML
    private Label productTitle, priceLabel;
    @FXML
    private TextField productBoxAmount;
    @FXML
    private ImageView addToFavorites;


    Product product;

    ShoppingItem shoppingItem;

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    iMatController controller;


    private int amount;


    public double total;

    public double getTotal() {
        return total;
    }


    public double getPrice() {
        return product.getPrice();
    }





    public ProductBoxItem(Product product, iMatController controller){
        IMatDataHandler dataHandler = IMatDataHandler.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("productBox.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);



        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        addToFavorites.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> changeFavorite());

        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        this.product = product;
        this.controller = controller;

        if(this.dataHandler.isFavorite(product)){
            this.addToFavorites.setImage(new Image(new File("src/iMat/resources/images/bättrast_hjärta.png").toURI().toString()));
        } else {
            this.addToFavorites.setImage(new Image(new File("src/iMat/resources/images/bättrast_hjärta_tom_transparent.png").toURI().toString()));
        }
        //System.out.println(file.toURI().toString());
        this.productImage.setImage(dataHandler.getFXImage(product));

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


        this.productTitle.setText(product.getName());
        this.productBoxAmount.setText(String.valueOf(amount));
        this.priceLabel.setText(String.valueOf(product.getPrice()) + " " + product.getUnit());

    }
    @FXML
    public void add() {
        controller.add(product);
        amount++;
        productBoxAmount.setText(String.valueOf(amount));
    }



    @FXML
    public void remove() { controller.minus(product);
        amount--;
        if(amount <= 0){
            amount = 0;
        }

        productBoxAmount.setText(String.valueOf(amount));
    }

    @FXML
    public void updateAmount(){
        if(shoppingItem == null) {
            dataHandler.getShoppingCart().addProduct(product);
            shoppingItem = controller.findShoppingItem(product);
        }
        int amount = Integer.parseInt(productBoxAmount.getText());
        this.shoppingItem.setAmount(amount);

        controller.updateShoppingCartList();
        this.amount = amount;
        if(this.amount <= 0){
            this.amount = 0;
        }
    }

    public void changeFavorite(){
        this.controller.changeFavorite(product);
        if(dataHandler.isFavorite(product)){
            this.addToFavorites.setImage(new Image(new File("src/iMat/resources/images/bättrast_hjärta.png").toURI().toString()));
        } else {
            this.addToFavorites.setImage(new Image(new File("src/iMat/resources/images/bättrast_hjärta_tom_transparent.png").toURI().toString()));
        }
    }


}