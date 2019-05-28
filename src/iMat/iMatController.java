package iMat;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

import java.net.URL;
import java.util.*;
import java.util.List;

public class iMatController implements Initializable {

    @FXML
    private Button switchSceneButton, favoritesButton, checkoutButton, myPageButton, clearShoppingCartButton, paymentButton, payButton, searchButton;

    @FXML
    private ImageView escapehatch, addToFavorites;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private FlowPane categoriesList, categoriesGrid, shoppingCartList;

    @FXML
    private TextField productBoxAmount, firstNameField, lastNameField, phoneField, mobileField, mailField, adressField, postCodeField;

    @FXML
    private TextField holderNameField, yearField, cardNumberField, cvcField, searchTextField;

    @FXML
    private Label mainLabel, totalLabel, quantityLabel;

    @FXML
    private ComboBox<String> deliveryMonthComboBox, cardTypeComboBox;

    @FXML
    private ComboBox<Integer> monthComboBox, deliveryDayComboBox;

    @FXML
    private FlowPane checkoutList, priorOrdersFlowPane, thankyouList;

    @FXML
    private Text total;


    @FXML
    ScrollPane categoriesScrollPane;

    ProductCategory[] categories = ProductCategory.class.getEnumConstants();// for att hämta alla kategorier

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    ProductCategory category;

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
        }else if (iMat.scene.equals("favorites.fxml")) {
            updateFavoriteGrid();
            updateCategoryList();
            updateShoppingCartList();
        }else if (iMat.scene.equals(("myPage.fxml"))) {
            comboBox();
            Platform.runLater(() -> cardTypeComboBox.requestFocus());
            loadMyPage();
        }else if (iMat.scene.equals("checkout.fxml")) {
            updateCheckoutList();
        }else if (iMat.scene.equals("payment.fxml")){
            loadPayment();
        }else if (iMat.scene.equals("thankyou.fxml")){
            loadThankYou();
        }



        // TODO
        //updatefavorites();
        /*searchTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if (newValue) {
                    //focusgained - do nothing
                } else {
                    search();
                }
            }
        });*/
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

    @FXML
    private void switchToPayment() throws  Exception {

        iMat.scene = "payment.fxml";
        iMat.switchScene(paymentButton, "payment.fxml");
    }

    @FXML
    private void switchToThankYou() throws Exception {
        dataHandler.placeOrder();
        iMat.scene = "thankyou.fxml";
        iMat.switchScene(payButton, "thankyou.fxml");
    }



    @FXML
    public void updateCategoryList() {

        for (ProductCategory pc : categories) {
            Product p = dataHandler.getProducts(pc).get(0);
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
        if(!iMat.scene.equals("categories.fxml")){
            iMat.scene = "categories.fxml";
        }
        categoriesScrollPane.setVvalue(0);
        categoriesGrid.getChildren().clear();
        this.category = category;
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

        int amount = 0;
        //System.out.println(dataHandler.getShoppingCart().getItems().get(0).getProduct());
        shoppingCartList.getChildren().clear();
        for (int i = dataHandler.getShoppingCart().getItems().size() - 1; i >= 0; i--) {
            amount += dataHandler.getShoppingCart().getItems().get(i).getAmount();
            shoppingCartList.getChildren().add(new ShoppingCartListItem(dataHandler.getShoppingCart().getItems().get(i).getProduct(), this, dataHandler.getShoppingCart().getItems().get(i).getAmount()));
        }


        totalLabel.setText(String.valueOf("Totalkostnad: " + dataHandler.getShoppingCart().getTotal()) + " kr");
        quantityLabel.setText(String.valueOf("Antal: " + amount + " st"));
    }

    @FXML
    public void updateCheckoutList() {
        checkoutList.getChildren().clear();
        for (int i = dataHandler.getShoppingCart().getItems().size() - 1; i >= 0; i--) {
            checkoutList.getChildren().add(new CheckoutProductBox(dataHandler.getShoppingCart().getItems().get(i), this));
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

        updateScene(product);
    }

    public void minus(Product product) {
        int change = -1;
        changeAmount(product, change);

        updateScene(product);
    }

    public void updateScene(Product product){
        if (iMat.scene.equals("checkout.fxml")) {
            updateCheckoutList();
        } else if (iMat.scene.equals("favorites.fxml")) {
            updateFavoriteGrid();
        } else if (iMat.scene.equals("categories.fxml")) {
            updateShoppingCartList();
            if(product.getCategory() == this.category) {
                updateProductGrid(product.getCategory());
            }

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
                return "Salt, Socker & Mjöl";
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
                return "''Örter''";

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
            orderBox orderBox = new orderBox(order, this);
            this.priorOrdersFlowPane.getChildren().add(orderBox);
        }

        System.out.println(dataHandler.getOrders().size());
    }

    public void loadPayment(){


        cardTypeComboBox.getItems().addAll("MasterCard", "Visa", "Maestro");
        monthComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        deliveryDayComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
        deliveryMonthComboBox.getItems().addAll("Januari","February","Mars","April","Maj","Juni","Juli", "Augusti","September","Oktober","November","December");

        Customer customer = dataHandler.getCustomer();
        CreditCard creditCard = dataHandler.getCreditCard();
        adressField.setText(customer.getAddress());
        postCodeField.setText(customer.getPostCode());
        yearField.setText(String.valueOf(creditCard.getValidYear()));
        monthComboBox.getSelectionModel().select(creditCard.getValidMonth());
        cardTypeComboBox.getSelectionModel().select(creditCard.getCardType());
        holderNameField.setText(creditCard.getHoldersName());
        cardNumberField.setText(creditCard.getCardNumber());
        cvcField.setText(String.valueOf(creditCard.getVerificationCode()));

        totalLabel.setText("Totalkostnad: " +  String.valueOf(dataHandler.getShoppingCart().getTotal()) + "kr");

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

        //dataHandler.placeOrder();
    }

    @FXML
    public void updateThankYouList(){
        thankyouList.getChildren().clear();

            Order order = dataHandler.getOrders().get(dataHandler.getOrders().size()-1);
            //thankyouList.getChildren().add(new orderBox(order, this));


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

    public void loadThankYou(){
        updateThankYouList();

    }

    @FXML
    public void clearShoppingCart(){
        dataHandler.getShoppingCart().getItems().clear();
        shoppingCartList.getChildren().clear();
        updateShoppingCartList();
    }

    public void search(){
        categoriesGrid.getChildren().clear();
        mainLabel.setText("Du har sökt på '" + searchTextField.getText() + "'");
        if (!searchTextField.getText().equals("")){
            for (Product p :dataHandler.getProducts()) {
             //   System.out.println(p.getName());
                   if (p.getName().toLowerCase().contains(searchTextField.getText().toLowerCase())){
                       categoriesGrid.getChildren().add(new ProductBoxItem(p, this));
                   }
            }
        }
    }
}

