package iMat;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.*;
import java.util.List;

public class iMatController implements Initializable {

    @FXML
    private Button switchSceneButton, favoritesButton, checkoutButton, myPageButton;

    @FXML
    private ImageView escapehatch, addToFavorites;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private FlowPane categoriesList, categoriesGrid, shoppingCartList;

    @FXML
    private TextField productBoxAmount, firstNameField, lastNameField, phoneField, mobileField, mailField, adressField, postCodeField;

    @FXML
    private TextField holderNameField, yearField, cardNumberField, cvcField;

    @FXML
    private Label mainLabel, totalLabel;

    @FXML
    private ComboBox<String> cardTypeComboBox;

    @FXML
    private ComboBox<Integer> monthComboBox;

    @FXML
    private FlowPane checkoutList, priorOrdersFlowPane;

    @FXML
    private Text total;


    @FXML
    ScrollPane categoriesScrollPane;

    ProductCategory[] categories = ProductCategory.class.getEnumConstants();// for att hämta alla kategorier

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    List<Product> products = dataHandler.getProducts();

    public void changeFavorite(Product product) {
        if (dataHandler.isFavorite(product)) {
            dataHandler.removeFavorite(product);
        } else {
            dataHandler.addFavorite(product);
        }
        //System.out.println("Favorited " + product.getName());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //dataHandler.addFavorite(25);

        if (iMat.scene.equals("categories.fxml")) {
            //System.out.println(dataHandler.getShoppingCart().getItems().size());
            updateCategoryGrid();
            updateCategoryList();
            updateShoppingCartList();
        }

        if (iMat.scene.equals("favorites.fxml")) {
            updateFavoriteGrid();
            updateCategoryList();
            updateShoppingCartList();
        }

        if (iMat.scene.equals(("myPage.fxml"))) {
            comboBox();
            Platform.runLater(() -> cardTypeComboBox.requestFocus());
            loadMyPage();
        }

        if (iMat.scene.equals("checkout.fxml")) {
            updateCheckoutList();
        }

        // TODO
        //updatefavorites();

    }

    @FXML
    private void switchToCategories() throws Exception {
        if (iMat.scene.equals("categories.fxml")) {
            //          mainLabel.setText("Kategorier");
        }
        iMat.escapehatch(escapehatch, "categories.fxml");

    }

    @FXML
    private void switchToFavorites() throws Exception {
        iMat.scene = "favorites.fxml";
        iMat.switchScene(favoritesButton, "categories.fxml");
    }

    @FXML
    private void switchToAccount() throws Exception {
        iMat.scene = "myPage.fxml";
        iMat.switchScene(myPageButton, "myPage.fxml");

    }

    @FXML
    private void switchToCheckout() throws Exception {

        iMat.scene = "checkout.fxml";
        iMat.switchScene(checkoutButton, "checkout.fxml");

    }


    public Image getSquareImage(Image image) {

        int x = 0;
        int y = 0;
        int width = 0;
        int height = 0;

        if (image.getWidth() > image.getHeight()) {
            width = (int) image.getHeight();
            height = (int) image.getHeight();
            x = (int) (image.getWidth() - width) / 2;
            y = 0;
        } else if (image.getHeight() > image.getWidth()) {
            width = (int) image.getWidth();
            height = (int) image.getWidth();
            x = 0;
            y = (int) (image.getHeight() - height) / 2;
        } else {
            //Width equals Height, return original image
            return image;
        }
        return new WritableImage(image.getPixelReader(), x, y, width, height);
    }

    @FXML
    public void updateCategoryList() {

        for (ProductCategory pc : categories) {
            Product p = dataHandler.getProducts(pc).get(1);
            categoriesList.getChildren().add(new CategoryListItem(p, this));
        }
    }

    @FXML
    public void updateCategoryGrid() {

        for (ProductCategory pc : categories) {
            Product p = dataHandler.getProducts(pc).get(0);
            categoriesGrid.getChildren().add(new CategoryBoxItem(p, this));
        }

    }

    @FXML
    public void updateProductGrid(ProductCategory category) {
        categoriesScrollPane.setVvalue(0);
        categoriesGrid.getChildren().clear();
        List<Product> products = dataHandler.getProducts(category);
        mainLabel.setText(switchName(products.get(0)));
        for (Product product : products) {
            categoriesGrid.getChildren().add(new ProductBoxItem(product, this));
        }

    }

    @FXML
    public void updateFavoriteGrid() {
        categoriesScrollPane.setVvalue(0);
        categoriesGrid.getChildren().clear();
        List<Product> products = dataHandler.favorites();
        mainLabel.setText("Favoriter");
        for (Product product : products) {
            categoriesGrid.getChildren().add(new ProductBoxItem(product, this));
        }
    }

    @FXML
    public void updateShoppingCartList() {

        //System.out.println(dataHandler.getShoppingCart().getItems().get(0).getProduct());
        shoppingCartList.getChildren().clear();
        for (int i = dataHandler.getShoppingCart().getItems().size() - 1; i >= 0; i--) {
            shoppingCartList.getChildren().add(new ShoppingCartListItem(dataHandler.getShoppingCart().getItems().get(i).getProduct(), this, dataHandler.getShoppingCart().getItems().get(i).getAmount()));
        }
        totalLabel.setText(String.valueOf("Totalkostnad: " + dataHandler.getShoppingCart().getTotal()) + " kr");
    }

    @FXML
    public void updateCheckoutList() {
        checkoutList.getChildren().clear();
        for (int i = dataHandler.getShoppingCart().getItems().size() - 1; i >= 0; i--) {
            checkoutList.getChildren().add(new CheckoutProductBox(dataHandler.getShoppingCart().getItems().get(i).getProduct(), this));
        }

        total.setText("Totalbelopp: " + dataHandler.getShoppingCart().getTotal() + " kr");

    }


    @FXML
    public void removeProductFromShoppingCart(ShoppingCartListItem item) {
        int change = -10000000;
        changeAmount(item.product, change);
        shoppingCartList.getChildren().remove(item);
    }

    public void add(Product product) {
        int change = 1;
        changeAmount(product, change);

        updateScene();

    }

    public void minus(Product product) {
        int change = -1;
        changeAmount(product, change);

        updateScene();
    }

    public void updateScene(){
        if (iMat.scene.equals("checkout.fxml")) {
            updateCheckoutList();
        } else if (iMat.scene.equals("favorites.fxml")) {
            updateFavoriteGrid();
        } else if (iMat.scene.equals("categories.fxml")) {
            updateShoppingCartList();
        }

    }

    public void removeItem(ShoppingItem item) {

        dataHandler.getShoppingCart().removeItem(item);
    }

    public void changeAmount(Product product, int change) {
        ShoppingCart shoppingCart = dataHandler.getShoppingCart();

        ShoppingItem shitem = findShoppingItem(product);
        double amount;
        if (shitem != null) {
            amount = (shitem.getAmount() + change);
            if (amount <= 0) {
                shoppingCart.removeItem(shitem);
            } else {
                shitem.setAmount(amount);
            }
        } else if (change > 0) {
            shoppingCart.addProduct(product);
        }


    }

    public void clearShoppingCartList() {
        shoppingCartList.getChildren().clear();
    }


    public String switchName(Product product) {
        String name = product.getCategory().name();

        switch (name) {
            case "POD":
                return "Böner & Ärtor";
            case "BREAD":
                return "Bröd";
            case "BERRY":
                return "Bär";
            case "CITRUS_FRUIT":
                return "Citrus Frukter";
            case "HOT_DRINKS":
                return "Varm Dryck";
            case "COLD_DRINKS":
                return "Kall Dryck";
            case "EXOTIC_FRUIT":
                return "Exotiska Frukter";
            case "FISH":
                return "Fisk";
            case "VEGETABLE_FRUIT":
                return "Grönsaker";
            case "CABBAGE":
                return "Kål";
            case "MEAT":
                return "Kött";
            case "DAIRIES":
                return "Mejeri";
            case "MELONS":
                return "Melon";
            case "FLOUR_SUGAR_SALT":
                return "Salt & Socker & Mjöl";
            case "NUTS_AND_SEEDS":
                return "Nötter";
            case "PASTA":
                return "Pasta";
            case "POTATO_RICE":
                return "Ris & Potatis";
            case "ROOT_VEGETABLE":
                return "Rotfrukter";
            case "FRUIT":
                return "Frukt";
            case "SWEET":
                return "Sötsaker";
            case "HERB":
                return "Örter";

        }
        return name;


    }

    public ShoppingItem findShoppingItem(Product product) {
        boolean found = false;
        ShoppingCart shoppingCart = dataHandler.getShoppingCart();

        for (ShoppingItem shitem : shoppingCart.getItems()) {
            if (shitem.getProduct() == product) {
                return shitem;
            }
        }
        return null;
    }

    public double returnPrice(Product product){
      return product.getPrice();
    }

    public void loadMyPage() {
        Customer customer = dataHandler.getCustomer();
        CreditCard creditCard = dataHandler.getCreditCard();


        cardTypeComboBox.getItems().addAll("MasterCard", "Visa", "Maestro");
        monthComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);

        //this.cardTypeComboBox.getItems().addAll("MasterCard","Visa", "Maestro");

        this.firstNameField.setText(customer.getFirstName());
        this.lastNameField.setText(customer.getLastName());
        this.phoneField.setText(customer.getPhoneNumber());
        this.mobileField.setText(customer.getMobilePhoneNumber());
        this.mailField.setText(customer.getEmail());
        this.adressField.setText(customer.getAddress());
        this.postCodeField.setText(customer.getPostCode());



        cardTypeComboBox.getSelectionModel().select(creditCard.getCardType());
        monthComboBox.getSelectionModel().select(creditCard.getValidMonth());

        this.holderNameField.setText(creditCard.getHoldersName());
        this.yearField.setText(String.valueOf(creditCard.getValidYear()));
        this.cardNumberField.setText(creditCard.getCardNumber());
        this.cvcField.setText(String.valueOf(creditCard.getVerificationCode()));

        this.priorOrdersFlowPane.getChildren().clear();
        for (Order order : dataHandler.getOrders()){

            this.priorOrdersFlowPane.getChildren().add(new orderBox(order, this));
        }

        System.out.println(dataHandler.getOrders().size());

    }

    public void saveMypage() {
        Customer customer = dataHandler.getCustomer();
        CreditCard creditCard = dataHandler.getCreditCard();

        customer.setFirstName(this.firstNameField.getText());
        customer.setLastName(this.lastNameField.getText());
        customer.setPhoneNumber(this.phoneField.getText());
        customer.setMobilePhoneNumber(this.mobileField.getText());
        customer.setEmail(this.mailField.getText());
        customer.setAddress(this.adressField.getText());
        customer.setPostCode(this.postCodeField.getText());

        //creditCard.setCardType(this.cardTypeField.getText());
        comboBox();
        creditCard.setCardType(cardTypeComboBox.getSelectionModel().getSelectedItem().toString());
        creditCard.setValidMonth(this.monthComboBox.getSelectionModel().getSelectedIndex());
        creditCard.setHoldersName(this.holderNameField.getText());
        creditCard.setValidYear(Integer.parseInt(this.yearField.getText()));
        creditCard.setCardNumber(this.cardNumberField.getText());
        creditCard.setVerificationCode(Integer.parseInt(this.cvcField.getText()));

        //System.out.println(customer.getFirstName());

        dataHandler.placeOrder();
    }

    public void comboBox() {

        CreditCard creditCard = dataHandler.getCreditCard();




        cardTypeComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String result = (String) cardTypeComboBox.getSelectionModel().getSelectedItem();

            creditCard.setCardType(result);
        });

        monthComboBox.getSelectionModel().select(creditCard.getValidMonth());

        monthComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int result = monthComboBox.getSelectionModel().getSelectedIndex();

            creditCard.setValidMonth(result);
        });

    }

}

