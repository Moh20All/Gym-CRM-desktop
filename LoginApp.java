package com.example.ramzigym;

import com.example.ramzigym.Helper.DatabaseHelper;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class LoginApp extends Application {
    @Override
    public void start(Stage stage) {
        DatabaseHelper.setupDatabase();
        try {
            // Load the FXML file
            URL fxmlUrl = getClass().getResource("/com/example/ramzigym/login.fxml");
            if (fxmlUrl == null) {
                throw new IOException("FXML file not found");
            }
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            Parent root = fxmlLoader.load();

            // Create the scene with the loaded FXML
            Scene scene = new Scene(root, 400, 600);

            // Load and apply the CSS file
            URL cssUrl = getClass().getResource("/com/example/ramzigym/styles/login.css");
            if (cssUrl == null) {
                throw new IOException("CSS file not found");
            }
            scene.getStylesheets().add(cssUrl.toExternalForm());

            // Set the title of the window
            stage.setTitle("Gym CRM Login");

            // Set the scene to the stage
            stage.setScene(scene);

            // Show the stage
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            // You can also show an alert dialog here
            // showErrorDialog("Error", "An error occurred while loading the application.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
