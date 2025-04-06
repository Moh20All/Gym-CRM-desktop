package com.example.ramzigym.controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

public class AddathleteController {

    // Group related buttons together
    @FXML
    private Button uploadPhotoButton, takePhotoButton, saveButton, editButton;
    @FXML
    private Button clientsButton, consumption, dashboardButton, debt, logOut, offers, settings, users;

    // Group related fields together
    @FXML
    private TextField firstNameField, lastNameField, assignTagField, contactField;
    @FXML
    private TextArea noteArea;
    @FXML
    private DatePicker dateOfSignIn;
    @FXML
    private RadioButton maleRadio, femaleRadio;
    @FXML
    private ComboBox<String> typeComboBox;
    @FXML
    private ImageView profileImage;

    // Group related containers together
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private VBox menuContainer;
    @FXML
    private BorderPane rootPane;
    @FXML
    public void initialize() {
        // Initialize combo box items
        typeComboBox.getItems().addAll("Regular", "Premium", "VIP");

        // Add event listeners
        saveButton.setOnAction(event -> saveAthlete());
        uploadPhotoButton.setOnAction(event -> uploadPhoto());
        takePhotoButton.setOnAction(event -> takePhoto());

        // Validate and save the data to a database or service
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
                        case F3:
                            loadSubScene("minimarket"); // Load 'minimarket' scene when F3 is pressed
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
    private void saveAthlete() {
        // Logic to save athlete data
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        LocalDate signInDate = dateOfSignIn.getValue();
        String gender = maleRadio.isSelected() ? "Male" : "Female";
        String assignTag = assignTagField.getText();
        String type = typeComboBox.getValue();
        String contact = contactField.getText();
        String note = noteArea.getText();


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
                profileImage.setImage(image);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                // Handle the case where the file is not found or cannot be opened
                showAlert("File Error", "Could not load the selected image. Please try again.");
            }
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }


    private void takePhoto() {
        // Logic to open camera and capture photo
    }
}
