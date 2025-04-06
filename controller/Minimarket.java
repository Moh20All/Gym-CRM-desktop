package com.example.ramzigym.controller;
import com.example.ramzigym.Model.Product;
import com.example.ramzigym.Model.*;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.fxml.FXML;

public class Minimarket {
    @FXML
    ImageView addproduct;
    @FXML
    private TextField editableTextField;

    @FXML
    private TextField editablepriceField;

    @FXML
    private Button saveButton;

    @FXML
    private Button clientsButton;

    @FXML
    private Button consumption;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button debt;


    @FXML
    private Button logOut;

    @FXML
    private VBox menuContainer;

    @FXML
    private Button offers;

    @FXML
    private BorderPane rootPane;


    @FXML
    private Button settings;

    @FXML
    private Button users;
    @FXML
    private ListView<CartItem> cartListView;  // Cart list in the UI
    @FXML
    private FlowPane itemsBox;  // HBox where product VBoxes will be added
    @FXML
    private Label totalPriceLabel;

    @FXML
    private VBox cart;

    // Cart item model to track cart entries
    private Map<String, CartItem> cartItems = new HashMap<>(); // To store cart items by product name

    @FXML
    void initialize(){
        addproduct.setOnMouseClicked(event -> uploadPhoto());
        // Save button action listener
        saveButton.setOnAction(event -> saveData());
        dashboardButton.setOnAction(event -> loadNewScene("mainMenu"));
        clientsButton.setOnAction(event -> loadNewScene("listOfClients"));
        offers.setOnAction(event -> loadNewScene("Offers"));
        consumption.setOnAction(event -> loadNewScene("Consumption"));
        debt.setOnAction(event -> loadNewScene("Debt"));
        users.setOnAction(event -> loadNewScene("Users"));
        settings.setOnAction(event -> loadNewScene("Settings"));
        logOut.setOnAction(event -> loadNewScene("mainMenu"));
        rootPane.sceneProperty().addListener((observable, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    switch (event.getCode()) {
                        case F2:
                            loadSubScene("Addathlete");
                            System.out.println("Athlete");// Load 'Addathlete' scene when F2 is pressed
                            break;
                        case ESCAPE:
                            loadNewScene("mainMenu"); // Load 'mainMenu' scene when ESCAPE is pressed

                            break;
                        default:
                            // Handle other keys if necessary or leave empty
                            break;
                    }
                });
            }
        });

        // Items adding
        // Create a list of products (could be dynamically loaded from a DB or API)
        List<Product> products = new ArrayList<>();
        products.add(new Product("Water 1.5L", "50da", "1.5l.jpg"));
        products.add(new Product("Creatine 100g", "2000da", "creatine.jpg"));
        products.add(new Product("Whey 100g", "2000da", "whey.png"));
        products.add(new Product("Whey aa100g", "2000da", "whey.png"));
        products.add(new Product("Whey a100g", "2000da", "whey.png"));
        // Dynamically add product VBoxes to the HBox
        for (Product product : products) {
            addProductToItems(product);
        }
    }


    private void loadSubScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ramzigym/subscreen/" + fxmlFile + ".fxml"));
            Parent newRoot = loader.load();

            if (rootPane == null || rootPane.getScene() == null) {
                throw new IllegalStateException("rootPane is not initialized properly.");
            }

            Scene scene = rootPane.getScene();
            scene.setRoot(newRoot);

            // Ensure maximized state remains unchanged
            Stage stage = (Stage) scene.getWindow();
            if (!stage.isMaximized()) {
                stage.setMaximized(true);
            }

            // Update stylesheet if it exists
            URL stylesheet = getClass().getResource("/com/example/ramzigym/styles/" + fxmlFile + ".css");
            scene.getStylesheets().clear(); // Clear previous styles
            if (stylesheet != null) {
                scene.getStylesheets().add(stylesheet.toExternalForm());
            } else {
                System.out.println("Warning: Stylesheet not found for " + fxmlFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
    // Method to add product to the HBox dynamically
    public void addProductToItems(Product product) {
        try {
            // Create a VBox to represent a product card
            VBox productBox = new VBox();
            productBox.setAlignment(Pos.CENTER);
            productBox.setSpacing(10);
            productBox.getStyleClass().add("product-card");
            productBox.setPrefWidth(150);
            productBox.setPrefHeight(194);

            // Retrieve the image URL
            String imagePath = "file:/C:/Users/Moh/Pictures/" + product.getImagePath();
            URL imageUrl = getClass().getResource(product.getImagePath());

            if (!imagePath.isEmpty()) {
                // Create the Image instance
                Image image = new Image(imagePath);
                System.out.println(imagePath);
                System.out.println(imageUrl);
                // Create ImageView for the product image
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(70);
                imageView.setFitWidth(70);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);

                // Add ImageView to the productBox
                productBox.getChildren().add(imageView);
            } else {
                System.out.println("Image not found: " + product.getImagePath());
            }

            // Create label for product name
            Label nameLabel = new Label(product.getName());
            nameLabel.getStyleClass().add("product-label");

            // Create label for product price
            Label priceLabel = new Label(product.getPrice() + " DA");
            priceLabel.getStyleClass().add("product-price");

            // Create 'Add to Cart' button
            Button addButton = new Button("Add to Cart");
            addButton.getStyleClass().add("add-cart-button");
            addButton.setOnAction(event -> {
                addToCart(product);
            });

            // Add the labels and button to the productBox
            productBox.getChildren().addAll(nameLabel, priceLabel, addButton);

            // Add the productBox to the beginning of the items HBox
            itemsBox.getChildren().add(0, productBox); // This will insert the productBox at the start of the HBox

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to add a product to the cart
    private void addToCart(Product product) {
        if (cartItems.containsKey(product.getName())) {
            // If the product is already in the cart, increase the quantity
            CartItem cartItem = cartItems.get(product.getName());
            cartItem.increaseQuantity();
            updateCartItemUI(cartItem);
        } else {
            // Product is not in the cart, create a new cart item
            CartItem newItem = new CartItem(product, 1);
            cartItems.put(product.getName(), newItem);
            addCartItemToUI(newItem);
        }
        // Update total price after adding an item
        updateTotalPrice();
    }

    // Adds a new CartItem to the cart UI
    private void addCartItemToUI(CartItem cartItem) {
        VBox cartItemBox = new VBox();
        cartItemBox.setSpacing(10);
        cartItemBox.getStyleClass().add("cart-item");

        // Load the product image
        String imagePath = "file:/C:/Users/Moh/Pictures/" + cartItem.getProduct().getImagePath();
        System.out.println(imagePath);

        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(40);
        imageView.setFitWidth(40);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);

        // Product Name Label
        Label nameLabel = new Label(cartItem.getProduct().getName());
        nameLabel.getStyleClass().add("cart-product-label");

        // Product Price Label
        Label priceLabel = new Label(cartItem.getProduct().getPrice() + " DA");
        priceLabel.getStyleClass().add("cart-product-price");

        // Quantity Label
        Label quantityLabel = new Label(String.valueOf(cartItem.getQuantity()));
        quantityLabel.getStyleClass().add("cart-quantity");

        // Buttons to increase/decrease quantity
        Button increaseButton = new Button("+");
        increaseButton.getStyleClass().add("cart-quantity-button");
        Button decreaseButton = new Button("-");
        decreaseButton.getStyleClass().add("cart-quantity-button");

        // HBox for quantity control
        HBox quantityBox = new HBox(10, increaseButton, quantityLabel, decreaseButton);
        quantityBox.setAlignment(Pos.CENTER_RIGHT);

        // HBox to hold the image, name, and price
        HBox productInfoBox = new HBox(10, imageView, nameLabel, priceLabel);
        productInfoBox.setAlignment(Pos.CENTER_LEFT);

        // Remove Button to remove item from cart
        Button removeButton = new Button("Remove");
        removeButton.getStyleClass().add("cart-quantity-button");

        // Event handlers for buttons
        increaseButton.setOnAction(event -> {
            cartItem.increaseQuantity();
            updateCartItemUI(cartItem); // Update the UI with new quantity
            updateTotalPrice(); // Recalculate total price
        });

        decreaseButton.setOnAction(event -> {
            if (cartItem.getQuantity() > 1) {
                cartItem.decreaseQuantity();
                updateCartItemUI(cartItem);
            } else {
                // Remove item if quantity is 1 and the user clicks the "-" button
                cart.getChildren().remove(cartItemBox);
                cartItems.remove(cartItem.getProduct().getName());
            }
            updateTotalPrice(); // Recalculate total price
        });

        removeButton.setOnAction(event -> {
            cart.getChildren().remove(cartItemBox);
            cartItems.remove(cartItem.getProduct().getName());
            updateTotalPrice(); // Recalculate total price
        });

        // Add product info, quantity box, and remove button to the cart item
        cartItemBox.getChildren().addAll(productInfoBox, quantityBox, removeButton);

        // Add the cart item to the VBox (cart)
        cart.getChildren().add(0, cartItemBox);
    }

    // Updates the quantity of an existing cart item in the UI
    private void updateCartItemUI(CartItem cartItem) {
        for (Node node : cart.getChildren()) {
            if (node instanceof VBox) {
                VBox itemBox = (VBox) node;

                // Ensure the first child is a Label for the product name
                if (itemBox.getChildren().get(0) instanceof Label) {
                    Label nameLabel = (Label) itemBox.getChildren().get(0);

                    // If the cart item matches the product name, update the quantity label
                    if (nameLabel.getText().equals(cartItem.getProduct().getName())) {
                        // Check that the third child is indeed an HBox before casting
                        if (itemBox.getChildren().size() > 2 && itemBox.getChildren().get(2) instanceof HBox) {
                            HBox hBox = (HBox) itemBox.getChildren().get(2);

                            // Ensure the second child of the HBox is a Label for the quantity
                            if (hBox.getChildren().size() > 1 && hBox.getChildren().get(1) instanceof Label) {
                                Label quantityLabel = (Label) hBox.getChildren().get(1);
                                quantityLabel.setText(String.valueOf(cartItem.getQuantity()));
                            } else {
                                System.out.println("Expected quantity label not found in HBox.");
                            }
                        } else {
                            System.out.println("Expected HBox not found as the third child of VBox.");
                        }
                    }
                } else {
                    System.out.println("Expected name label not found as the first child of VBox.");
                }
            }
        }
    }


    // Recalculates and updates the total price in the UI
    private void updateTotalPrice() {
        double total = 0;

        // Iterate through all cart items to calculate the total price
        for (CartItem item : cartItems.values()) {
            // Use parsePrice to clean the price string
            double price = parsePrice(item.getProduct().getPrice());
            total += price * item.getQuantity();
        }

        // Update the total label with the calculated price
        totalPriceLabel.setText(String.format("%.2f DA", total));
        System.out.println(total);
    }


    public double parsePrice(String priceWithCurrency) {
        // Remove all non-numeric characters except for the decimal point
        String numericValue = priceWithCurrency.replaceAll("[^\\d.]", "");

        // Parse the cleaned string to a double
        return Double.parseDouble(numericValue);
    }


    private void loadNewScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ramzigym/" + fxmlFile + ".fxml"));
            Parent newRoot = loader.load();

            if (rootPane == null || rootPane.getScene() == null) {
                throw new IllegalStateException("rootPane is not initialized properly.");
            }

            Scene scene = rootPane.getScene();
            scene.setRoot(newRoot);

            // Ensure maximized state remains unchanged
            Stage stage = (Stage) scene.getWindow();
            if (!stage.isMaximized()) {
                stage.setMaximized(true);
            }

            // Update stylesheet if it exists
            URL stylesheet = getClass().getResource("/com/example/ramzigym/styles/" + fxmlFile + ".css");
            scene.getStylesheets().clear(); // Clear previous styles
            if (stylesheet != null) {
                scene.getStylesheets().add(stylesheet.toExternalForm());
            } else {
                System.out.println("Warning: Stylesheet not found for " + fxmlFile);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void saveData() {
        // Get text from the editable fields
        String productName = editableTextField.getText();
        String productPrice = editablepriceField.getText();

        // Perform the saving logic here (e.g., saving to a database or updating UI)
        System.out.println("Product Name: " + productName);
        System.out.println("Product Price: " + productPrice);

        // You can add validation logic to ensure the fields aren't empty or invalid
    }


    @FXML
    private void uploadPhoto() {
        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Profile Photo");

        // Set the extension filter for image files
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);

        // Open the file dialog
        File selectedFile = fileChooser.showOpenDialog(new Stage());

        if (selectedFile != null) {
            // Try to load the image
            try {
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                Image image = new Image(fileInputStream);

                // Set the image in the ImageView
                addproduct.setImage(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                // Handle the case where the file is not found or cannot be opened
            }
        }
    }
}
