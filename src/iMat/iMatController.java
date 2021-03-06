package iMat;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import se.chalmers.cse.dat216.project.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.*;
import java.util.List;

public class iMatController implements Initializable {

    @FXML
    private Button switchSceneButton, favoritesButton, checkoutButton, myPageButton, clearShoppingCartButton, paymentButton, payButton, searchButton, backButton, toCheckOut, payButtonTop, backToCategories, backToCheckout;

    @FXML
    private Button  cancelSearch;

    @FXML
    private ImageView escapehatch, addToFavorites;

    @FXML
    private AnchorPane checkoutAnchorPane, searchAnchorPane, myPageAnchorPane;

    @FXML
    private FlowPane categoriesList, categoriesGrid, shoppingCartList;

    @FXML
    private TextField productBoxAmount, firstNameField, lastNameField, phoneField, mobileField, mailField, adressField, postCodeField;

    @FXML
    private TextField holderNameField, yearField, cardNumberField, cvcField, searchTextField;

    @FXML
    private Label mainLabel, totalLabel, quantityLabel, dateLabel, adressLabel, postCodeLabel;

    @FXML
    private ComboBox<String> deliveryMonthComboBox, cardTypeComboBox;

    @FXML
    private ComboBox<Integer> monthComboBox, deliveryDayComboBox;

    @FXML
    private FlowPane checkoutList, priorOrdersFlowPane, thankYouList;

    @FXML
    private Text total;


    @FXML
    ScrollPane categoriesScrollPane;

    ProductCategory[] categories = ProductCategory.class.getEnumConstants();// for att hämta alla kategorier

    IMatDataHandler dataHandler = IMatDataHandler.getInstance();

    ProductCategory category;

    List<Product> products = dataHandler.getProducts();

    static int day = 30;
    static String month = "Maj";
    static double finalPrice;
    String searchWord;
    Boolean isSearching = false;
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
    }

    @FXML
    private void switchToCategories() throws Exception {
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
        finalPrice = dataHandler.getShoppingCart().getTotal();
        dataHandler.placeOrder();

        month = deliveryMonthComboBox.getSelectionModel().getSelectedItem();
        day = deliveryDayComboBox.getSelectionModel().getSelectedItem();
        saveAtCheckoout();
        iMat.scene = "thankyou.fxml";
        iMat.switchScene(payButton, "thankyou.fxml");
    }

    @FXML
    private  void backToCheckout() throws  Exception {
        month = deliveryMonthComboBox.getSelectionModel().getSelectedItem();
        day = deliveryDayComboBox.getSelectionModel().getSelectedItem();
        iMat.scene = "checkout.fxml";
        iMat.switchScene(backToCheckout, "checkout.fxml");
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
        isSearching = false;
        backButton.setVisible(false);
        for (ProductCategory pc : categories) {
            Product p = dataHandler.getProducts(pc).get(0);
            categoriesGrid.getChildren().add(new CategoryBoxItem(p, this));
        }

    }

    @FXML
    public void updateProductGrid(ProductCategory category) {
        isSearching = false;
        if(!iMat.scene.equals("categories.fxml")){
            iMat.scene = "categories.fxml";
        }
        backButton.setVisible(true);
        categoriesScrollPane.setVvalue(0);
        categoriesGrid.getChildren().clear();
        this.category = category;
        List<Product> products = dataHandler.getProducts(category);
        mainLabel.setText(switchName(products.get(0)));
        for (Product product : products) {
            categoriesGrid.getChildren().add(new ProductBoxItem(product, this));
        }
        categoriesList.getChildren().clear();
        for (ProductCategory c: categories){
            if(c.equals(category)){
                categoriesList.getChildren().add(new CategoryListItemEffect(dataHandler.getProducts(category).get(0), this));

            }else{
                categoriesList.getChildren().add(new CategoryListItem(dataHandler.getProducts(c).get(0), this));
            }
        }
    }

    @FXML
    public void updateFavoriteGrid() {
        isSearching = false;
        if(!iMat.scene.equals("categories.fxml")){
            iMat.scene = "categories.fxml";
        }
        backButton.setVisible(true);
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
        shoppingCartList.getChildren().clear();
        for (int i = dataHandler.getShoppingCart().getItems().size() - 1; i >= 0; i--) {
            amount += dataHandler.getShoppingCart().getItems().get(i).getAmount();
            shoppingCartList.getChildren().add(new ShoppingCartListItem(dataHandler.getShoppingCart().getItems().get(i).getProduct(), this, dataHandler.getShoppingCart().getItems().get(i).getAmount()));
        }
        totalLabel.setText(String.valueOf("Totalbelopp: " + round(dataHandler.getShoppingCart().getTotal())) + " kr");
        quantityLabel.setText(String.valueOf("Antal: " + amount + " st"));
    }

    @FXML
    public void updateCheckoutList() {
        checkoutList.getChildren().clear();
        for (int i = dataHandler.getShoppingCart().getItems().size() - 1; i >= 0; i--) {
            checkoutList.getChildren().add(new CheckoutProductBox(dataHandler.getShoppingCart().getItems().get(i), this));
        }

        total.setText("Totalbelopp: " + round(dataHandler.getShoppingCart().getTotal()) + " kr");

        if(dataHandler.getShoppingCart().getItems().isEmpty()){
            paymentButton.setDisable(true);
        }else{
            paymentButton.setDisable(false);
        }
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
            if (searchAnchorPane.isVisible()) {
                updateSearchGrid(searchWord);
            }
        } else if (mainLabel.getText().equals("Favoriter")) {
            updateFavoriteGrid();
            updateShoppingCartList();
        }else if (iMat.scene.equals("myPage.fxml")) {
            if (isSearching){
                updateSearchGrid(searchWord);
            }
        } else if (iMat.scene.equals("categories.fxml")) {
            updateShoppingCartList();
            if (isSearching){
                updateSearchGrid(searchWord);
            }else {
                if(product.getCategory() == this.category) {
                    updateProductGrid(product.getCategory());
                }
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
                return "Citrusfrukter";
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
        for (int i = dataHandler.getOrders().size() - 1; i >= 0; i--){
            Order order = dataHandler.getOrders().get(i);
            orderBox orderBox = new orderBox(order, this);
            this.priorOrdersFlowPane.getChildren().add(orderBox);
        }

        //System.out.println(dataHandler.getOrders().size());
    }

    public void loadPayment(){


        cardTypeComboBox.getItems().addAll("MasterCard", "Visa", "Maestro");
        monthComboBox.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        deliveryDayComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31);
        deliveryMonthComboBox.getItems().addAll("Januari","Februari","Mars","April","Maj","Juni","Juli", "Augusti","September","Oktober","November","December");

        deliveryDayComboBox.getSelectionModel().select(day-1);
        deliveryMonthComboBox.getSelectionModel().select(month);

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

        totalLabel.setText("Totalkostnad: " +  round(dataHandler.getShoppingCart().getTotal()) + "kr");

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

    public void saveAtCheckoout(){
        Customer customer = dataHandler.getCustomer();
        CreditCard creditCard = dataHandler.getCreditCard();
        if (!adressField.getText().equals("")){
            customer.setAddress(this.adressField.getText());
        }
        if (!postCodeField.getText().equals("")){
            customer.setPostCode(this.postCodeField.getText());
        }
    }

    public void loadThankYou(){
        Customer customer = dataHandler.getCustomer();
        adressLabel.setText(customer.getAddress());
        postCodeLabel.setText(customer.getPostCode());
        totalLabel.setText("Pris " + dataHandler.getOrders().get(dataHandler.getOrders().size()-1).getItems().toString() + " kr");
        dateLabel.setText(day + " " + month);
        totalLabel.setText(String.valueOf(round(finalPrice)) + " kr");

        updateThankYouList();
    }

    @FXML
    public void updateThankYouList(){
        thankYouList.getChildren().clear();

            Order order = dataHandler.getOrders().get(dataHandler.getOrders().size()-1);
            thankYouList.getChildren().add(new orderBox(order, this));

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



    @FXML
    public void clearShoppingCart(){
        if (!dataHandler.getShoppingCart().getItems().isEmpty()){
            Product product = dataHandler.getShoppingCart().getItems().get(0).getProduct();
            dataHandler.getShoppingCart().getItems().clear();
            shoppingCartList.getChildren().clear();
            updateScene(product);
        }
    }

    public void search(){
        isSearching = true;
        searchWord = searchTextField.getText().toLowerCase();
        if (iMat.scene.equals("categories.fxml")){
            backButton.setVisible(true);

        }else if(iMat.scene.equals("myPage.fxml")){
            myPageAnchorPane.setVisible(false);
            searchAnchorPane.setVisible(true);
            myPageAnchorPane.toBack();
        }else {
            cancelSearch.setVisible(true);
            backButton.setVisible(false);
            checkoutAnchorPane.setVisible(false);
            searchAnchorPane.setVisible(true);
            checkoutAnchorPane.toBack();
        }

        updateSearchGrid(searchWord);
        mainLabel.setText("Sökningen gav " + categoriesGrid.getChildren().size() + " träffar");

    }

    public void updateSearchGrid(String word){
        categoriesGrid.getChildren().clear();
        for (Product p :dataHandler.getProducts()) {
            if (p.getName().toLowerCase().contains(word)){
                categoriesGrid.getChildren().add(new ProductBoxItem(p, this));
            }
        }
        if (iMat.scene.equals("myPage.fxml")) {
            backButton.setOnAction((event) -> {
                try {
                    switchToAccount();
                } catch (Exception e){

                }
            });
        }

    }

    public void keyListener(KeyEvent event){
        if(event.getCode() == KeyCode.ENTER) {
            search();
            ((TextField)event.getSource()).clear(); // clear textfield
        }
    }

    public double round(double value) {
       return(round(value, 2));
    }

    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public String getMainLabel(){

        return mainLabel.getText();
    }

}

