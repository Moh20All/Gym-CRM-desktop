package com.example.ramzigym.controller;

import com.example.ramzigym.Model.Athlete;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.DefaultStringConverter;

import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class Debtcontroller implements Initializable {
    @FXML
    private Label Date;

    @FXML
    private ImageView Debt;

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
    private TableView<Athlete> athletesTable;

    @FXML
    private Button clientsButton;

    @FXML
    private Button consumption;

    @FXML
    private Button dashboardButton;

    @FXML
    private TableColumn<Athlete, String> dateColumn;

    @FXML
    private Button debt;

    @FXML
    private TableColumn<Athlete, String> idColumn;

    @FXML
    private Button logOut;

    @FXML
    private VBox menuContainer;

    @FXML
    private TableColumn<Athlete, String> nameColumn;

    @FXML
    private TableColumn<Athlete, String> noteColumn;

    @FXML
    private Button offers;

    @FXML
    private BorderPane rootPane;

    @FXML
    private Button settings;

    @FXML
    private TableColumn<Athlete, String> totaldebtColumn;

    @FXML
    private Button users;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        idColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.2)); // 12.5% of table width
        nameColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.2));        idColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.2)); // 12.5% of table width
        totaldebtColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.2)); // 18.75% of table width
        dateColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.2)); // 12.5% of table width
        noteColumn.prefWidthProperty().bind(athletesTable.widthProperty().multiply(0.2));
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
