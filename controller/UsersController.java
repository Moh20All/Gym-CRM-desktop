package com.example.ramzigym.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class UsersController {
    @FXML
    private TableColumn<?, ?> addColumn;

    @FXML
    private TableView<?> athletesTable;

    @FXML
    private Button clientsButton;

    @FXML
    private Button consumption;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button debt;

    @FXML
    private TableColumn<?, ?> debtColumn;

    @FXML
    private TableColumn<?, ?> deleteColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private Button logOut;

    @FXML
    private VBox menuContainer;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private Button offers;

    @FXML
    private TableColumn<?, ?> passwordColumn;

    @FXML
    private TableColumn<?, ?> recipeColumn;

    @FXML
    private BorderPane rootPane;

    @FXML
    private TableColumn<?, ?> saleColumn;

    @FXML
    private Button settings;

    @FXML
    private Button users;

    @FXML
    private TableColumn<?, ?> usersColumn;
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
                    if (event.getCode() == KeyCode.ESCAPE) {
                        // Trigger the action for F1
                        loadNewScene("mainMenu"); // Load the help scene when F1 is pressed
                    }
                });
            }
        });

        // Calculate the percentage width for each column
        idColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.11)); // 11% of table width
        nameColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.11));
        passwordColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.11));
        recipeColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.11));
        deleteColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.11));
        addColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.11));
        saleColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.11));
        debtColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.11));
        usersColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.12));
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
