package com.example.ramzigym;
import com.example.ramzigym.Helper.DatabaseHelper;
import javafx.animation.FillTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Create a sample dashboard card
        Rectangle card = new Rectangle(200, 100);
        card.setArcWidth(20);
        card.setArcHeight(20);
        card.setFill(Color.WHITE);
        card.setStyle("-fx-padding: 20; -fx-border-radius: 20; -fx-background-radius: 20;");

        // Load CSS file
        Scene scene = new Scene(new StackPane(card), 300, 250);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        // Define hover animations
        FillTransition hoverTransition = new FillTransition(Duration.millis(400), card, Color.WHITE, Color.web("#b369ff"));
        FillTransition exitTransition = new FillTransition(Duration.millis(400), card, Color.web("#b369ff"), Color.WHITE);

        card.setOnMouseEntered(event -> hoverTransition.play());
        card.setOnMouseExited(event -> exitTransition.play());

        primaryStage.setTitle("JavaFX Hover Animation Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
