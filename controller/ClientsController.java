package com.example.ramzigym.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ClientsController {

    @FXML
    private Label Date;

    @FXML
    private Label Name;

    @FXML
    private Label Note;

    @FXML
    private Label Phone;

    @FXML
    private Label RFID;

    @FXML
    private Label Type;

    @FXML
    private BorderPane rootPane;

    @FXML
    private VBox menuContainer;

    @FXML
    private Button clientsButton;
    @FXML
    private Button offers;
    @FXML
    private Button consumption;

    @FXML
    private Button dashboardButton;
    @FXML
    private TableView<?> athletesTable;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> remainingSessionColumn;

    @FXML
    private TableColumn<?, ?> dateOfEndColumn;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> noteColumn;
    @FXML
    private Button logOut;

    @FXML
    private Button debt;


    @FXML
    private Button settings;

    @FXML
    private Button users;

    @FXML
    private void initialize() {
        // Set button actions
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
                            loadSubScene("Addathlete"); // Load 'Addathlete' scene when F2 is pressed
                            break;
                        case F3:
                            loadSubScene("minimarket"); // Load 'minimarket' scene when F3 is pressed
                            break;
                        case ESCAPE:
                            loadNewScene("mainMenu"); // Load 'mainMenu' scene when ESCAPE is pressed
                            System.out.println("Amain");
                            break;
                        default:
                            // Handle other keys if necessary or leave empty
                            break;
                    }
                });
            }
        });
        // Calculate the percentage width for each column
        idColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.10)); // 10% of table width
        nameColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.20)); // 20% of table width
        remainingSessionColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.20)); // 20% of table width
        dateOfEndColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.20)); // 20% of table width
        typeColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.15)); // 15% of table width
        noteColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.15)); // 15% of table width
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
}
