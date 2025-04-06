package com.example.ramzigym.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;
    // Capture Enter Key Press

    @FXML
    private void handleLoginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidCredentials(username, password)) {
            openMainMenu();
        } else {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Incorrect username or password.");
        }
    }

    private boolean isValidCredentials(String username, String password) {
        // Example validation logic, replace with actual authentication
        return "".equals(username) && "".equals(password);
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void openMainMenu() {
        try {
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/ramzigym/mainMenu.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 1200, 760);
            stage.setMaximized(true);

            // Apply the CSS stylesheet
            scene.getStylesheets().add(getClass().getResource("/com/example/ramzigym/styles/mainMenu.css").toExternalForm());

            stage.setScene(scene);
            stage.show();

            // Close the login window (optional)
            Stage loginStage = (Stage) usernameField.getScene().getWindow();
            loginStage.close();
        } catch (IOException e) {
            e.printStackTrace();
            // You can also show an alert dialog here
            // showErrorDialog("Error", "An error occurred while opening the main menu.");
        }
    }

    public void handleKeyPress(javafx.scene.input.KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            handleLoginAction();
        }
    }
}
